# language: nl
Functionaliteit: Voorbeeld
  Achtergrond:
    Stel ik begin een gesprek met de Google Home applicatie via phone
    En ik ben ingelogd als user met id "foo"

  Scenario: Test
    Als ik zeg "Hoe gaat het?"
    Dan begrijpt de assistente dat ik "Hoe gaat het" bedoel
    En ze vraagt "Waarom wil je dat weten?"
    Als ik zeg "Nou, gewoon"
    Dan begrijpt ze dat ik de "Hoe gaat het - nou gewoon" intentie heb
    En de assistente zegt "Slecht"
    En een eenvoudige kaart wordt getoond met:
    	| title         | Title    |
    	| subtitle      | SubTitle |
    	| formattedText | Text     |
    	
