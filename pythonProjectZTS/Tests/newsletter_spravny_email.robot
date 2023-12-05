*** Settings ***
Library    SeleniumLibrary
Library    Collections

*** Variables ***
${URL}    https://www.martinus.sk/
${Browser}    chrome

*** Test Cases ***
Test Newsletter Spravny Email
    ${random_email}=    Generate Random Email
    Open Browser    ${URL}    ${Browser}
    Maximize Browser Window
    Click Element    id=CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll
    Click Element    id=newsletter-input
    Input Text    id=newsletter-input    ${random_email}
    Click Element    css=.btn--primary
    Execute JavaScript    window.scrollBy(0, 80)
    Click Element    css=.btn--primary
    Wait Until Element Is Visible    css=.alert__content    timeout=10s
    ${alert_content}=    Get Text    css=.alert__content
    Should Contain    ${alert_content}    Ďakujeme za zadanie e-mailovej adresy. Na mail ${random_email} sme vám poslali e-mail, kliknite na odkaz v ňom pre aktivovanie odosielania newsletterov na túto adresu.
    Close Browser

*** Keywords ***
Generate Random Email
    ${random_string}=    Evaluate    ''.join(__import__('random').choice(__import__('string').ascii_lowercase) for _ in range(8))
    ${random_email}=    Catenate    SEPARATOR=    ${random_string}    @example.com
    [Return]    ${random_email}
