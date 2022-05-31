$(document).ready(function () {
    hello();
})

function add_comments(memoId){


    let comments = $('#user_comment').val();
    let data={"memoId" : memoId,"comments":comments};

    $.ajax({
        type: "POST",
        url: "/api/memos/comments",
        contentType: "application/json",
        data: JSON.stringify(data),
        success: function (response) {
            alert('댓글이 성공적으로 저장되었습니다.');
            window.location.reload();
        }
    });
}
function hello(){
}