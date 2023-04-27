package com.fee.calculator.feeCalculator;


import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/fee")
public class FeeController {

    // Mock JSON data
    private final String feeJson = "{\"Exam Fee\":{\"INDIAN\":{\"ALL_COURSES\":{\"ALL_LEVEL\":{\"amount\":400}}},\"FOREIGN\":{\"ALL_COURSES\":{\"ALL_LEVEL\":{\"amount\":100}}},\"NRI\":{\"ALL_COURSES\":{\"ALL_LEVEL\":{\"amount\":600}}},\"SAARC\":{\"ALL_COURSES\":{\"ALL_LEVEL\":{\"amount\":600}}}},\"Application Fee\":{\"INDIAN\":{\"ALL_COURSES\":{\"UG\":{\"amount\":200},\"UG-DIPLOMA\":{\"amount\":300},\"PG\":{\"amount\":500}}},\"FOREIGN\":{\"ALL_COURSES\":{\"UG\":{\"amount\":400},\"UG-DIPLOMA\":{\"amount\":400},\"PG\":{\"amount\":700}}}}}}";

    @GetMapping("/{feeType}/{nationality}/{course}/{level}")
    @CrossOrigin
    public ResponseEntity<String> getFee(@PathVariable String feeType, @PathVariable String nationality, @PathVariable String course, @PathVariable String level) throws JSONException {
        // Parse JSON string to a JSON object
        JSONObject feeJsonObj = new JSONObject(feeJson);

        // Get the fee object based on the selected fee type
        if(feeJsonObj.has(feeType)){
            JSONObject selectedFee = feeJsonObj.getJSONObject(feeType);
            if(selectedFee.has(nationality)){
                JSONObject nationalityJson = selectedFee.getJSONObject(nationality);
                if(nationalityJson.has(course)){
                    JSONObject courseJson = nationalityJson.getJSONObject(course);
                    if (courseJson.has(level)){
                        Integer amount = (Integer) courseJson.getJSONObject(level).get("amount");
                        return ResponseEntity.ok(amount.toString());
                    }else {
                        return ResponseEntity.badRequest().body("Invalid level selection. Please try again.");
                    }
                }else {
                    return ResponseEntity.badRequest().body("Invalid course selection. Please try again.");
                }
            } else {
                return ResponseEntity.badRequest().body("Invalid nationality selection. please try again");
            }
        } else {
            return ResponseEntity.badRequest().body("Invalid fee selection. please try again");
        }
    }
}
