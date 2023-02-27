// datatable 공통옵션
let dataTableOpt = {
    // dom : "<'top' lB>frtip",
    // buttons :[
    //     {
    //     extend: "excel",
    //     text: "엑셀다운로드 1212",
    //     className: "listBox__btns btn--pg btn--border btn--border--clr", 
    //     title: "엑셀 ㅋ"
    //     }
    // ],
    filter : false,
    deferRender : true,
    destroy: true, // 데이터 테이블 삭제 가능
    lengthChange : true, //데이터건수 변경
    lengthMenu: [[20, 50], ["20개 보기", "50개 보기"]], //데이터건수옵션 
    paging : true,  //페이징처리
    pagingType : "full_numbers", 
    pageLength: 20, //기본 데이터건수
    info: false,
    searching : false, //검색
    autoWidth: false, //가로자동
    ordering: false,
    responsive: true,
    scrollX: "100%", 
    scrollXInner: "100%",
    createdRow :function (row, data, dataIndex, full) {
		// tr 접근
        $(row).attr('class', "listTbl__tr");

		// td 접근
        $(row).children('td').attr('class', 'listTbl__tbody__td');
    },
    language : {
        "info": "<strong>_TOTAL_</strong>건 &middot; _PAGE_/_PAGES_Page",
        "infoEmpty": "검색결과가 없습니다."
    },
};
