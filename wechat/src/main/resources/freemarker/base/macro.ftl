<#macro message key>${messageResolver.getMessage(key)}</#macro> 

<#macro label key><@message "LABEL.${key}"/></#macro> 

<#macro button key><@message "BUTTON.${key}"/></#macro> 

<#macro title key><title><@message "TITLE.${key}"/></title></#macro> 

<#macro info key><@message "INFO.${key}"/></#macro> 
