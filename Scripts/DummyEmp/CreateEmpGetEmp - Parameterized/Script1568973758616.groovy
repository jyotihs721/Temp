import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import groovy.json.JsonSlurper

Date date = new Date()
String datevalue = date.toString()
datevalue = datevalue.replace(" ", "")
datevalue = datevalue.replace(":", "")

println datevalue

GlobalVariable.EmpName = datevalue
println GlobalVariable.EmpName


//response = WS.sendRequest(findTestObject('DummyEmployee/Obj_CreateEmp'))
response = WS.sendRequestAndVerify(findTestObject('DummyEmployee/Obj_CreateEmpGetEmp - Paremeterized', ['EmpName':datevalue,'salary':salary,'age':age]))
println response.getResponseBodyContent()

String responseText = response.getResponseBodyContent()

//generic Verification of string
//assert WS.containsString(response, datevalue, false), OR
//specific verification of element property
WS.verifyElementPropertyValue(response, 'name', GlobalVariable.EmpName)

WS.verifyResponseStatusCode(response, 200)

println response.getResponseBodyContent()

JsonSlurper slurper = new JsonSlurper()
Map parsedJson = slurper.parseText(responseText)

String idValue = parsedJson.id
String id = parsedJson.get("id")

println id
GlobalVariable.EmpId = id

println GlobalVariable.EmpId

response = WS.sendRequest(findTestObject('DummyEmployee/Obj_GetEmp'))

println response.getResponseBodyContent()

WS.verifyResponseStatusCode(response, 200)

