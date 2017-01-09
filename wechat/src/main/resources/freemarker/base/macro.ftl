#macro(message $key)${messageResolver.getMessage($key)}#end 

#macro(label $key)#message("LABEL.$key")#end 

#macro(button $key)#message("BUTTON.$key")#end 

#macro(title $key)<title>#message("TITLE.$key")</title>#end 

#macro(info $key)#message("INFO.$key")#end