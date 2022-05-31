$(document).ready(function () {
    hello();
})

function add_comments(){
    alert("댓글저장!")

    let comments = $('#user_comment').val();
    let data={"memoId" : 10,"comments":comments};

    $.ajax({
        type: "POST",
        url: "/api/memos/comments",
        contentType: "application/json",
        data: JSON.stringify(data),
        success: function (response) {
            alert('메시지가 성공적으로 작성되었습니다.');
            window.location.reload();
        }
    });
}
function hello(){
    alert("댓글페이지에요!")
}