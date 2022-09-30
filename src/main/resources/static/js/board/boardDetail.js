const message = {
    callBackConfirm: function (obj) {
        let opt = $.extend({title: document.title, msg: '처리하겠습니까?', callback: ''}, obj);
        let $div = $('<div id="callBackConfirm"></div>');
        $('body').append($div);
        $($div).kendoDialog({
            title: opt.title,
            content: opt.msg,
            minWidth: 300,
            minHeight: 150,
            closable: false,
            actions: [
                {text: '취소'},
                {
                    text: '확인',
                    action: function () {
                        if (typeof opt.callback === 'function') {
                            opt.callback.call();
                        }
                        return true;
                    },
                    primary: true
                }
            ],
            close: function () {
                $($div).data('kendoDialog').destroy();
            }

        });
    }
}
$("#free-board-detail-popup-title").text('게시글 상세보기');
var appbar = $("#appbar").kendoAppBar({
    themeColor: "inherit",
    items: [
        {
            template: '<a id="logo" href="/board"> <img src="/images/centerlink.png" alt="logo" style="width: 100%"> </a>',
            type: "contentItem"
        },

        {width: 1, type: "spacer"},
        {
            template: '<button id="home-top-btn"></button>', type: "contentItem"
        },
        {width: 16, type: "spacer"}
    ]
});
$("#free-board-detail-body-splitter").kendoSplitter({
    orientation: 'vertical',
    panes: [
        {collapsible: false, resizable: false},
        {collapsible: false, size: '100px', resizable: false}
    ]
});
$("#free-board-detail-reg-date").kendoTextBox({
    fillMode: 'flat',
    readonly: true
});
$("#free-board-detail-modi-date").kendoTextBox({
    fillMode: 'flat',
    readonly: true
});
$("#free-board-detail-like").kendoButton({
    themeColor: 'base',
    click: () => {
        message.callBackConfirm({msg: '추천 하시겠습니까?', callback: new likeInsert().like});

    }
});
$('#free-board-detail-comment-write').kendoTextBox({
    placeholder : "댓글을 입력해 주세요.",
}).on("keyup",(e) => {
    if (e.keyCode == 13) {
        $("#free-board-detail-comment-btn").trigger("click");
    }
});

// 댓글 추가
function replySub(){
    const boardNum = Number($("#free-board-detail-board-num").val());
    const param = {
        board_num : Number($("#free-board-detail-board-num").val()),
        reply_contets : $("#free-board-detail-comment-write").data("kendoTextBox").value(),
    }

    $.ajax({
        type: 'POST',
        url: "v1/replyAdd/"+boardNum,
        data:JSON.stringify(param) ,
        contentType: 'application/json',
        success: function (){
            alert("이이잉");
        }
    })
}
$('#free-board-detail-comment-btn').kendoButton({
    click : () => {
        replySub();
        // const replyInsert = replySub().getDataSource();
        // console.log(replyInsert);
        // replyInsert.read().then(()=>{
        //     $("#free-board-detail-comment-write").data("kendoTextBox").value("");
        //     $("#free-board-detail-comment-list-view").data("kendoListView").dataSource.read();
        // })
    }
});

$("#free-board-detail-delete-btn").kendoButton({
    themeColor: 'base',
    click: () => {
        if (userIdx !== dbUserIdx) {
            alert("자신이 작성한 글만 삭제할 수 있습니다.");
        } else {
            message.callBackConfirm({msg: '삭제 하시겠습니까?', callback: new boardDel().deleteOne});
        };
    }

});
$("#free-board-detail-update-btn").kendoButton({
    themeColor: 'info',
    click: () => {
        const boardNum = Number($("#free-board-detail-board-num").val());
        if (userIdx !== dbUserIdx) {
            alert("자신이 작성한 글만 수정할 수 있습니다.");
        } else {
            window.location.href = '/modify/' + boardNum;
        };
    }
});

boardRegidate = kendo.toString(new Date(boardRegidate), "yyyy-MM-dd H:mm");
boardModidate = kendo.toString(new Date(boardModidate), "yyyy-MM-dd H:mm");
$("#free-board-detail-user-num").val(userIdx)
$("#free-board-detail-board-num").val(boardNum);
$("#free-board-detail-title").text(boardTitle);
$("#free-board-detail-contents").text(boardContets);
$("#free-board-detail-reg-date").data("kendoTextBox").value(boardRegidate);
$("#free-board-detail-modi-date").data("kendoTextBox").value(boardModidate);

const freeBoardDetailDataSource = {
    replySelectDataSource: () => {
        const boardNum = Number($("#free-board-detail-board-num").val());
        return new kendo.data.DataSource({
            transport: {
                read: {
                    url: '/v1/replyList/'+boardNum,
                    type: "GET",
                    dataType : "json",
                    contentType: 'application/json; charset=utf=8'
                },
            },
            schema: {
                model: {
                    replyContents : {type : 'string'},
                    replyRegidate : {type: 'date'},
                    replyMember : {type: 'string'}
                },
                parse : (res) => {
                    console.log(res);
                    res.forEach((data)=>{
                        data.replyRegidate = kendo.toString(new Date(data.replyRegidate), "yyyy-MM-dd H:mm");
                    })
                    return res;
                }
            }
        })
    }
}


$('#free-board-detail-comment-list-view').kendoListView({
    height : "90%",
    dataSource: freeBoardDetailDataSource.replySelectDataSource(),
    layout: "flex",
    scrollable : true,
    flex: {
        direction: "column"
    },
    template: kendo.template($("#free-board-comment-listview").html())
});

class boardDel {
    deleteOne() {
        const boardNum = Number($("#free-board-detail-board-num").val());
        $.ajax({
            url: '/v1/delete/' + boardNum,
            contentType: "application/json; charset=utf-8",
            success: window.location.href = '/board'
        });
    };
    // deleteReply() {
    //     $.ajax({
    //         url: '/v1/deleteReply/'+replyNum,
    //         contentType: "application/json; charset=utf-8",
    //         success: window.location.href = `/v1/detailOne/${item.boardNum}`
    //     });
    // };

}

class likeInsert {
    like() {
        const boardNum = Number($("#free-board-detail-board-num").val());
        $.ajax({
            type: "POST",
            url: '/v1/updateLike/' + boardNum,
            contentType: "application/json; charset=utf-8",
            success: () =>{
                location.reload()
                $("#likeCnt").text(likeCnt)
            }
        });


    }
}

