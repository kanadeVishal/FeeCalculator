 function getDetails() {
        const feeType = document.getElementById('feeType').value;
        const nationality = document.getElementById('nationality').value;
        const course = document.getElementById('course').value;
        const level = document.getElementById('level').value;
        var url = `http://localhost:8080/fee/${feeType}/${nationality}/${course}/${level}`;
}