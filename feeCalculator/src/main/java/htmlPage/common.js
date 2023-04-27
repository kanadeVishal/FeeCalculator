'use strict';
function calculateFee() {
        const feeType = document.getElementById('feeType').value;
        const nationality = document.getElementById('nationality').value;
        const course = document.getElementById('course').value;
        const level = document.getElementById('level').value;
        var url = `http://localhost:8080/fee/${feeType}/${nationality}/${course}/${level}`;
    console.log(url);

    $.ajax({
        url:url,
        type: 'GET',
        async: false,
        dataType: "json",
        contentType: "application/json",
        //data: JSON.stringify(jsonData),
        success: function(resp1) {
            $('#result-message').text("Fees is :"+(resp1));
        },

          error: function(jqXHR, textStatus, errorThrown) {
                if (jqXHR != 200) {
                        // $('#result-message').text("Fees is :"+(resp1));
                         console.error('API request failed:', textStatus, errorThrown)
                    }
                }

    });

}