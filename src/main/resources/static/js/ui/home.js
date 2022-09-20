let mainTabs;
kendo.culture('ko-KR');
$(document).ready(function() {
    document.title = CENTER_NAME;
    $("#appbar").kendoAppBar({
        themeColor: "inherit",
        items: [
            { template: '<h3 class="title">센터링크</h3>', type: "contentItem" },
            { width: 16, type: "spacer"}
        ]
    });
});