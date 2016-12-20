$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("src/main/resources/ServiceNowLogin_LogOut.feature");
formatter.feature({
  "line": 1,
  "name": "Navigate to https://dev20709.service-now.com/navpage.do Site",
  "description": "",
  "id": "navigate-to-https://dev20709.service-now.com/navpage.do-site",
  "keyword": "Feature"
});
formatter.before({
  "duration": 108289688,
  "status": "passed"
});
formatter.scenario({
  "line": 3,
  "name": "Verify ServiceNow SignUp",
  "description": "",
  "id": "navigate-to-https://dev20709.service-now.com/navpage.do-site;verify-servicenow-signup",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 5,
  "name": "I go to \"https://dev20709.service-now.com/navpage.do\" on \"chrome\"",
  "keyword": "Given "
});
formatter.step({
  "line": 6,
  "name": "I enter text into \"usernametextbox\" as \"admin\"",
  "keyword": "And "
});
formatter.step({
  "line": 7,
  "name": "I enter text into \"passwordtextbox\" as \"Google@123\"",
  "keyword": "And "
});
formatter.step({
  "line": 8,
  "name": "I click on \"siginInbtn\"",
  "keyword": "And "
});
formatter.step({
  "line": 9,
  "name": "I click on \"userInfo\"",
  "keyword": "And "
});
formatter.step({
  "line": 10,
  "name": "I clicked \"logOutlink\"",
  "keyword": "And "
});
formatter.match({
  "arguments": [
    {
      "val": "https://dev20709.service-now.com/navpage.do",
      "offset": 9
    },
    {
      "val": "chrome",
      "offset": 58
    }
  ],
  "location": "StepDef.I_go_to(String,String)"
});
formatter.result({
  "duration": 16427774946,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "usernametextbox",
      "offset": 19
    },
    {
      "val": "admin",
      "offset": 40
    }
  ],
  "location": "StepDef.I_enter_text_into(String,String)"
});
formatter.result({
  "duration": 5255174266,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "passwordtextbox",
      "offset": 19
    },
    {
      "val": "Google@123",
      "offset": 40
    }
  ],
  "location": "StepDef.I_enter_text_into(String,String)"
});
formatter.result({
  "duration": 5155941682,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "siginInbtn",
      "offset": 12
    }
  ],
  "location": "StepDef.I_click_on(String)"
});
formatter.result({
  "duration": 16560014642,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "userInfo",
      "offset": 12
    }
  ],
  "location": "StepDef.I_click_on(String)"
});
formatter.result({
  "duration": 3131906148,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "logOutlink",
      "offset": 11
    }
  ],
  "location": "StepDef.I_clicked(String)"
});
formatter.result({
  "duration": 5695215215,
  "status": "passed"
});
formatter.after({
  "duration": 1405549550,
  "status": "passed"
});
});