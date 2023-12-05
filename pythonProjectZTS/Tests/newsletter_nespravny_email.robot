*** Settings ***
Library    SeleniumLibrary

*** Variables ***
${URL}    https://www.martinus.sk/
${Browser}    chrome

*** Test Cases ***
Test Newsletter Nespravny Email
    Open Browser    ${URL}    ${Browser}
    Maximize Browser Window
    Click Element    id=CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll
    Click Element    id=newsletter-input
    Input Text    id=newsletter-input    zts.example.com
    Click Element    css=.btn--primary
    Wait Until Element Is Visible    css=.form-text:nth-child(4)    timeout=10s
    ${error_message}=    Get Text    css=.form-text:nth-child(4)
    Should Be Equal As Strings    ${error_message}    Zadaná e-mailová adresa je neplatná.
    Close Browser
