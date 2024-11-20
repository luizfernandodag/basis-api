#INSTALANDO OS PACOTES NECESSÁRIOS
  #install.packages("haven")
  # install.packages("dplyr")
#IMPORTANDO OS PACOTES NECESSÁRIOS

library(haven)
library(dplyr)
#CARREGANDO OS DADOS



  ## dados de goias
GO = read_dta("GO.dta")
  ## Pesquisa Nacional por Amostra de Domicílios de goias
PNADdom_GO <- read_dta("PNADdom_GO.dta")



#QUESTOES

  ##Q1 


GO <- na.omit(GO, c("rendap", "horasp","anosest"))
filter(GO, rendap > 0)
#filter(GO, log(rendap))

filter(GO, anosest > 0)
filter(GO, horasp > 0)


GO <- GO[complete.cases(GO$rendap), ]
GO <- GO[complete.cases(GO$anosest), ]
GO <- GO[complete.cases(GO$horasp), ]







GO <- GO[complete.cases(GO$rendap), ]
GO <- GO[complete.cases(GO$rendap), ]





  ### Calcular ecriar coluna rendh
funRENDH <- function(x, y) {x/y}
GO$rendh <- mapply(funRENDH, GO$rendap, GO$horasp)
GO$LOGrendh = log(GO$rendh)
GO <- subset(GO, !is.infinite(LOGrendh))
  ### plotar rendap X anosest


plot(GO$anosest, GO$rendap,
     main="RENDAP X ANOSEST",
     ylab="RENDAP",
     xlab="ANOSEST")

correlacao = cor(GO$rendap, GO$anosest)
print(correlacao)

  ##Q2

#linear model
model <- lm(rendh ~ anosest, data=GO)

# summary
summary(model)

  ##Q3
  ### homoscedaciadde das vairancias
 
  
  beta1 <- coef(model)["anosest"]
  se_beta1 <- summary(model)$coefficients["anosest", "Std. Error"]
  t_stat <- beta1 / se_beta1
  p_valor <- 1 - pt(t_stat, df = nrow(GO) - 2)
  
  nivel_de_significancia <- 0.01
  if (p_valor < nivel_de_significancia) {
    cat("O coeficiente Beta1 é estatisticamente maior que zero (nível de 1% de significância).")
  } else {
    cat("Não há evidências estatísticas de que o coeficiente Beta1 seja maior que zero.")
  }
  
  ##Q4

  ##Q5

  GO$Y_log <- log(GO$rendh)
  #GO$X_log <- log(GO$anosest)
  
  # Ajustar o modelo de regressão usando os logaritmos
  modelo_log <- lm(Y_log ~ anosest, data = GO)
  
  # Visualizar o resumo do modelo
  summary(modelo_log)

  ##Q6 
  tetha1 <- coef(modelo_log)["anosest"]
  se_theta1 <- summary(modelo_log)$coefficients["anosest", "Std. Error"]
  t_stat <- beta1 / se_theta1
  p_valor <- 1 - pt(t_stat, df = nrow(GO) - 2)
  
  nivel_de_significancia <- 0.01
  if (p_valor < nivel_de_significancia) {
    cat("O coeficiente THETA1 é estatisticamente maior que zero (nível de 1% de significância).")
  } else {
    cat("Não há evidências estatísticas de que o coeficiente Theta1 seja maior que zero.")
  }

  ##Q7

  ##Q8
  
  modelo_log2 <- lm(Y_log ~ anosest + mulher + negro , data = GO)
  
  # Visualizar o resumo do modelo
  summary(modelo_log2)
  
  ##Q6 
  tetha1 <- coef(modelo_log2)["anosest"]
  tetha2 <- coef(modelo_log2)["mulher"]
  tetha3 <- coef(modelo_log2)["negro"]
  
  
  se_theta1 <- summary(modelo_log2)$coefficients["anosest", "Std. Error"]
  se_theta2 <- summary(modelo_log2)$coefficients["mulher", "Std. Error"]
  se_theta3 <- summary(modelo_log2)$coefficients["negro", "Std. Error"]
  
  t_stat1 <- tetha1 / se_theta1
  t_stat2 <- tetha2 / se_theta2
  t_stat3 <- tetha3 / se_theta3
  
  
  p_valor1 <- 1 - pt(t_stat1, df = nrow(GO) - 2)
  p_valor2 <- 1 - pt(t_stat2, df = nrow(GO) - 2)
  p_valor3 <- 1 - pt(t_stat3, df = nrow(GO) - 2)
  
  
  
  
  nivel_de_significancia <- 0.01
  if (p_valor1 < nivel_de_significancia) {
    cat("O coeficiente THETA1 (anosest) é estatisticamente maior que zero (nível de 1% de significância).")
  } else {
    cat("Não há evidências estatísticas de que o coeficiente Theta1 (anosest) seja maior que zero.")
  }
  
  if (p_valor2 < nivel_de_significancia) {
    cat("O coeficiente THETA2 (mulher) é estatisticamente maior que zero (nível de 1% de significância).")
  } else {
    cat("Não há evidências estatísticas de que o coeficiente Theta2 (mulher) seja maior que zero.")
  }
  
  if (p_valor3 < nivel_de_significancia) {
    cat("O coeficiente THETA1 (negro) é estatisticamente maior que zero (nível de 1% de significância).")
  } else {
    cat("Não há evidências estatísticas de que o coeficiente Theta3 (negro) seja maior que zero.")
  }

  ##Q9

  ##Q10

  nivel_de_significancia <- 0.05
  if (p_valor1 < nivel_de_significancia) {
    cat("O coeficiente THETA1 (anosest) é estatisticamente maior que zero (nível de 5% de significância).")
  } else {
    cat("Não há evidências estatísticas de que o coeficiente Theta1 (anosest) seja maior que zero.")
  }
  
  if (p_valor2 < nivel_de_significancia) {
    cat("O coeficiente THETA2 (mulher) é estatisticamente maior que zero (nível de 5% de significância).")
  } else {
    cat("Não há evidências estatísticas de que o coeficiente Theta2 (mulher) seja maior que zero.")
  }
  
  if (p_valor3 < nivel_de_significancia) {
    cat("O coeficiente THETA1 (negro) é estatisticamente maior que zero (nível de 5% de significância).")
  } else {
    cat("Não há evidências estatísticas de que o coeficiente Theta3 (negro) seja maior que zero.")
  }


  ##Q11 


  ##Q12

