

/*  댓글등록 */
let replyWrite = (movieId) => {
	$("#btn_write").attr("disabled", true);
	const ratings = document.getElementsByName("rate");
	let selectedRating;
	for (let rating of ratings) {
		if (rating.checked) {
			selectedRating = rating.value;
			break;
		}
	}
	let formData = {
		movieId: movieId,
		review: $("#review").val(),
		rating: selectedRating
	};


	console.log(formData);

	$.ajax({
		type: "POST",
		url: "/review/write/" + movieId,
		data: formData,
		success: (resp) => {
			console.log(resp);
			let obj = JSON.parse(resp);
			if (obj.result === 'success') {
				Swal.fire({
					title: obj.message,
					icon: 'success',
				}).then((result) => {
					if (result.isConfirmed) {

						/* $('#review').val('') //댓글 등록시 댓글 등록창 초기화 */
						/* getReplyList();  //등록후 댓글 목록 불러오기 메서드호출 */
						location.href = "/movie/printOne/" + movieId;
					}

				})
			} else if (obj.result === 'fail') {
				Swal.fire({
					title: obj.message,
					icon: 'error'
				})
			}


		}

	});

}


/*  댓글목록출력 */
$(document).ready(() => {
	
    console.log(window.location.pathname);
	$.ajax({
		url: "/review/printAll/"+ "/" + window.location.pathname ,
		data: {
			"movieId": window.location.pathname
		},
		type: "get",
		success: function(rlist) { //댓글목록 불러오는 함수
			var $tableBody = $('#rtb tbody'); //$는 의미없음 그냥 변수명 중 하나
			$tableBody.html(''); //tbody를 초기화 시켜야 댓글 목록의 중첩을 막을수 있음 아니면 등록할떄마다 append로 이어짐
			$('#rCount').text(" (" + rlist.length + ")  ") //댓글수 출력
			if (rlist != null) {
				console.log(rlist);
				for (var i in rlist) {
					var $tr = $("<tr>");
					var $writerNickname = $("<td width='100'>").text(
						rlist[i].writerNickname);
					var $review = $("<td>").text(
						rlist[i].review);
					var stars = "";
					for (var j = 0; j < rlist[i].rating; j++) {
						stars += "&#9733;"; // 이 문자열은 별 모양의 HTML 엔티티입니다.
					}
					var $rating = $("<td width='50'>").html(stars);
 
					$tr.append($writerNickname);

					$tr.append($rating);
					$tr.append($review);
					/* $tr.append($btnArea); */
					$tableBody.append($tr);

				}
			}

		},
		error: function() {
			console.log("요청실패");

		}
	})


})





/* 	  const btn = document.querySelector("button");
	  const post = document.querySelector(".post");
	  const widget = document.querySelector(".star-widget");
	  const editBtn = document.querySelector(".edit");
	  btn.onclick = ()=>{
		widget.style.display = "none";
		post.style.display = "block";
		editBtn.onclick = ()=>{
		  widget.style.display = "block";
		  post.style.display = "none";
		}
		return false;
	  } */

