$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("example.feature");
formatter.feature({
  "comments": [
    {
      "line": 1,
      "value": "# language: nl"
    }
  ],
  "line": 2,
  "name": "Voorbeeld",
  "description": "",
  "id": "voorbeeld",
  "keyword": "Functionaliteit"
});
formatter.background({
  "line": 3,
  "name": "",
  "description": "",
  "type": "background",
  "keyword": "Achtergrond"
});
formatter.step({
  "line": 4,
  "name": "ik begin een gesprek met de \"Test\" Google Home applicatie",
  "keyword": "Stel "
});
formatter.match({
  "arguments": [
    {
      "val": "Test",
      "offset": 29
    }
  ],
  "location": "DialogflowSteps.java:20"
});
formatter.result({
  "duration": 73236261,
  "status": "passed"
});
formatter.scenario({
  "line": 6,
  "name": "Test",
  "description": "",
  "id": "voorbeeld;test",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 7,
  "name": "ik zeg \"Hoe gaat het?\"",
  "keyword": "Als "
});
formatter.step({
  "line": 8,
  "name": "begrijpt de assistente dat ik \"Hoe gaat het\" bedoel",
  "keyword": "Dan "
});
formatter.step({
  "line": 9,
  "name": "ze vraagt \"Waarom wil je dat weten?\"",
  "keyword": "En "
});
formatter.step({
  "line": 10,
  "name": "ik zeg \"Nou, gewoon\"",
  "keyword": "Als "
});
formatter.step({
  "line": 11,
  "name": "begrijpt ze dat ik de \"Hoe gaat het - nou gewoon\" intentie heb",
  "keyword": "Dan "
});
formatter.step({
  "line": 12,
  "name": "de assistente zegt \"Goed\"",
  "keyword": "En "
});
formatter.match({
  "arguments": [
    {
      "val": "Hoe gaat het?",
      "offset": 8
    }
  ],
  "location": "DialogflowSteps.java:27"
});
formatter.result({
  "duration": 1812487603,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "Hoe gaat het",
      "offset": 31
    }
  ],
  "location": "DialogflowSteps.java:35"
});
formatter.result({
  "duration": 114225111,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "Waarom wil je dat weten?",
      "offset": 11
    }
  ],
  "location": "DialogflowSteps.java:39"
});
formatter.result({
  "duration": 46539,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "Nou, gewoon",
      "offset": 8
    }
  ],
  "location": "DialogflowSteps.java:27"
});
formatter.result({
  "duration": 500099250,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "Hoe gaat het - nou gewoon",
      "offset": 23
    }
  ],
  "location": "DialogflowSteps.java:42"
});
formatter.result({
  "duration": 18058879,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "Goed",
      "offset": 20
    }
  ],
  "location": "DialogflowSteps.java:46"
});
formatter.result({
  "duration": 126585,
  "status": "passed"
});
});