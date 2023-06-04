/** @format */

var medicalHistoryIndex = 1;
var examinationIndex = 1;

function addMedicalHistory() {
    var container = document.getElementById("medicalHistoryContainer");
    var entry = document.createElement("div");
    entry.className = "medicalHistoryEntry";
    entry.innerHTML =
        `<input type="text" id="medicalHistory${medicalHistoryIndex}.name" name="medicalHistory[${medicalHistoryIndex}].name" placeholder="Name" /><br/>`+
        `<input type="text" id="medicalHistory${medicalHistoryIndex}.detail" name="medicalHistory[${medicalHistoryIndex}].detail" placeholder="Detail" /><br/>`;
    container.appendChild(entry);
    medicalHistoryIndex++;
}

function addExamination() {
    var container = document.getElementById("examinationContainer");
    var entry = document.createElement("div");
    entry.className = "examinationEntry";
    entry.innerHTML =
        `<input type="text" id="examination${examinationIndex}.name" name="examination[${examinationIndex}].name" placeholder="Name" /><br/>` +
        `<input type="text" id="examination${examinationIndex}.detail" name="examination[${examinationIndex}].detail" placeholder="Detail" /><br/>`;
    container.appendChild(entry);
    examinationIndex++;
}
