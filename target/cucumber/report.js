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
  "location": "DialogflowSteps.java:15"
});
formatter.result({
  "duration": 85147680,
  "status": "passed"
});
formatter.scenario({
  "line": 6,
  "name": "Test",
  "description": "Wanneer ik zeg \"Hoe gaat het?\"",
  "id": "voorbeeld;test",
  "type": "scenario",
  "keyword": "Scenario"
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
      "val": "Hoe gaat het",
      "offset": 31
    }
  ],
  "location": "DialogflowSteps.java:27"
});
formatter.result({
  "duration": 67279,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "Waarom wil je dat weten?",
      "offset": 11
    }
  ],
  "location": "DialogflowSteps.java:30"
});
formatter.result({
  "duration": 50810,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "Nou, gewoon",
      "offset": 8
    }
  ],
  "location": "DialogflowSteps.java:22"
});
formatter.result({
  "duration": 1021916358,
  "error_message": "javax.ws.rs.NotFoundException: HTTP 404 Not Found\n\tat org.glassfish.jersey.client.JerseyInvocation.convertToException(JerseyInvocation.java:1095)\n\tat org.glassfish.jersey.client.JerseyInvocation.translate(JerseyInvocation.java:894)\n\tat org.glassfish.jersey.client.JerseyInvocation.access$700(JerseyInvocation.java:98)\n\tat org.glassfish.jersey.client.JerseyInvocation$2.call(JerseyInvocation.java:776)\n\tat org.glassfish.jersey.internal.Errors.process(Errors.java:315)\n\tat org.glassfish.jersey.internal.Errors.process(Errors.java:297)\n\tat org.glassfish.jersey.internal.Errors.process(Errors.java:228)\n\tat org.glassfish.jersey.process.internal.RequestScope.runInScope(RequestScope.java:407)\n\tat org.glassfish.jersey.client.JerseyInvocation.invoke(JerseyInvocation.java:772)\n\tat org.glassfish.jersey.client.JerseyInvocation$Builder.method(JerseyInvocation.java:454)\n\tat org.glassfish.jersey.client.JerseyInvocation$Builder.post(JerseyInvocation.java:355)\n\tat com.github.flock_se.dialogflow_ci.dialogflow.helper.RequestHelper.query(RequestHelper.java:30)\n\tat com.github.flock_se.dialogflow_ci.dialogflow.Dialogflow.say(Dialogflow.java:19)\n\tat com.github.flock_se.dialogflow_ci.steps.DialogflowSteps.lambda$new$1(DialogflowSteps.java:24)\n\tat ✽.Als ik zeg \"Nou, gewoon\"(example.feature:10)\n",
  "status": "failed"
});
formatter.match({
  "arguments": [
    {
      "val": "Hoe gaat het - nou gewoon",
      "offset": 23
    }
  ],
  "location": "DialogflowSteps.java:33"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "arguments": [
    {
      "val": "Goed",
      "offset": 20
    }
  ],
  "location": "DialogflowSteps.java:36"
});
formatter.result({
  "status": "skipped"
});
});