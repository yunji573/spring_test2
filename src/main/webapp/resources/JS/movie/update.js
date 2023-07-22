$(document).ready(function() {
    var grade = "${movieDTO.grade}";  // 서버에서 가져온 값
    $("#grade").val(grade);  // 선택된 option 설정
});