import os
import sys
from dbConnector.M20PSQLConnector import *
from psycopg2 import sql

class M20TestElement(M20Element):

    name = 'tabella1'

    def __init__(self, id="", value=""):
        M20Element.__init__(self)
        self.id = id
        self.value = value

        self.insertQuery = "INSERT INTO %s (id, val) VALUES (%s, '%s')"%(self.name, self.id, self.value)
        print self.insertQuery

    @classmethod
    def createQuery(cls):
        return """CREATE TABLE IF NOT EXISTS %s
                                    (id SERIAL PRIMARY KEY ,
                                     val TEXT);"""%cls.name


if __name__ == '__main__':

    m20Connector = M20PSQLConnector('data_reply1_db', 'dario1', 'localhost', 'password', clearTableInit=True)
    m20Connector.createDict[M20TestElement.name] = M20TestElement.createQuery()

    m20Connector.connect()
    m20Connector.initDB()
    m20Connector.insert(M20TestElement(1, "val1"))
    m20Connector.insert(M20TestElement(2, "val2"))
    m20Connector.insert(M20TestElement(3, "val3"))

    m20Connector.printAll(M20TestElement)

    m20Connector.close()

