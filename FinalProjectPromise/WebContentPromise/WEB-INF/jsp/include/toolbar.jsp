<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!-- 	This is toolbar -->
	<div class="container bg-info" style="border-radius: 3px;background-color: #E8F4FA">
		<logic:notEmpty name="btnBackVisible">
			<logic:equal name="btnBackVisible" value="true">
				<button id="btnBack" type="button" onclick="onBtnBackClick();"
					class="btn btn-raised btn-info btn-icon" title="Back">
					<span class="glyphicon glyphicon-arrow-left" aria-hidden="true"></span>
				</button>
			</logic:equal>
		</logic:notEmpty>
		<logic:notEmpty name="btnAddVisible">
			<logic:equal name="btnAddVisible" value="true">
				<button id="btnAdd" type="button" onclick="onBtnAddClick();"
					class="btn btn-raised btn-info btn-icon" title="Add">
					<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
				</button>
			</logic:equal>
		</logic:notEmpty>
		<logic:notEmpty name="btnSaveVisible">
			<logic:equal name="btnSaveVisible" value="true">
				<button id="btnSave" type="button" onclick="onBtnSaveClick();"
					class="btn btn-raised btn-info btn-icon" title="Save">
					<span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>
				</button>
			</logic:equal>
		</logic:notEmpty>
		<logic:notEmpty name="btnApproveVisible">
			<logic:equal name="btnApproveVisible" value="true">
				<button id="btnApprove" type="button" onclick="onBtnApproveClick();"
					class="btn btn-raised btn-info btn-icon" title="Approve">
					<span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
				</button>
			</logic:equal>
		</logic:notEmpty>
		<logic:notEmpty name="btnDeclineVisible">
			<logic:equal name="btnDeclineVisible" value="true">
				<button id="btnDecline" type="button" onclick="onBtnDeclineClick();"
					class="btn btn-raised btn-info btn-icon" title="Decline">
					<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
				</button>
			</logic:equal>
		</logic:notEmpty>
	</div>
<!-- 	toolbar end -->