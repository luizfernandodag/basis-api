from configparser import ConfigParser
import os 
#p = os.getcwd()
p = os.path.dirname(os.path.realpath(__file__)) + '\s2.ini'

print(p)
config = ConfigParser()
config.read(p)
print(config.sections())
print(config['DEV']['username'])



#C:\Users\Luiz\Documents\Trabalho\schief.ai\DOING