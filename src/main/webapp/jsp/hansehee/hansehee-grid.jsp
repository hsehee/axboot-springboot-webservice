<%@ page contentType="text/html; charset=UTF-8" %> <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <%@ taglib prefix="ax"
tagdir="/WEB-INF/tags" %>

<ax:set key="title" value="${pageName}" />
<ax:set key="page_desc" value="${PAGE_REMARK}" />
<ax:set key="page_auto_height" value="true" />

<ax:layout name="base">
    <jsp:attribute name="script">
        <script type="text/javascript" src="<c:url value='/assets/js/view/hansehee/hansehee-grid.js' />"></script>
    </jsp:attribute>
    <jsp:body>
        <ax:page-buttons></ax:page-buttons>

        <div role="page-header">
            <ax:form name="searchView0">
                <ax:tbl clazz="ax-search-tbl" minWidth="500px">
                    <ax:tr>
                        <ax:td label="회사명" width="300px">
                            <input type="text" name="company" id="company" class="form-control" />
                        </ax:td>
                        <ax:td label="대표자" width="300px">
                            <input type="text" name="ceo" id="ceo" class="form-control" />
                        </ax:td>
                        <ax:td label="사업자번호" width="300px">
                            <input type="text" name="bizno" id="bizno" class="form-control" />
                        </ax:td>
                        <ax:td label="사용여부" width="300px">
                            <select name="useYn" class="form-control js-useYn">
                                <option value="">전체</option>
                                <option value="Y">사용</option>
                                <option value="N">미사용</option>
                            </select>
                        </ax:td>
                    </ax:tr>
                </ax:tbl>
            </ax:form>
            <div class="H10"></div>
        </div>

        <ax:split-layout name="ax1" orientation="horizontal">
            <ax:split-panel width="*" style="">
                <!-- 목록 -->
                <div class="ax-button-group" data-fit-height-aside="grid-view-01">
                    <div class="left">
                        <h2><i class="cqc-list"></i> 프로그램 목록</h2>
                    </div>
                    <div class="right">
                        <button type="button" class="btn btn-default" data-grid-view-01-btn="add"><i class="cqc-circle-with-plus"></i> 추가</button>
                        <button type="button" class="btn btn-default" data-grid-view-01-btn="delete">
                            <i class="cqc-circle-with-plus"></i> 삭제
                        </button>
                    </div>
                </div>
                <div data-ax5grid="grid-view-01" data-fit-height-content="grid-view-01" style="height: 300px"></div>
            </ax:split-panel>
        </ax:split-layout>
    </jsp:body>
</ax:layout>
