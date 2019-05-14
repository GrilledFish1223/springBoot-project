# -*- coding: utf-8 -*-

'''
    使用kafka-Python 1.3.3模块
'''

import sys
import time
import json

from kafka import KafkaProducer
from kafka import KafkaConsumer
from kafka.errors import KafkaError

KAFAKA_HOST = "127.0.0.1"
KAFAKA_PORT = 9092
KAFAKA_TOPIC = "foobar"


class Kafka_producer():
    '''
    生产模块：根据不同的key，区分消息
    '''

    def __init__(self, kafkahost, kafkaport, kafkatopic, key):
        self.kafkaHost = kafkahost
        self.kafkaPort = kafkaport
        self.kafkatopic = kafkatopic
        self.key = key
        self.producer = KafkaProducer(bootstrap_servers='{kafka_host}:{kafka_port}'.format(
            kafka_host=self.kafkaHost,
            kafka_port=self.kafkaPort)
        )

    def sendjsondata(self, params):
        try:
            parmas_message = json.dumps(params)
            producer = self.producer
            producer.send(self.kafkatopic, key=self.key, value=parmas_message.encode('utf-8'))
            producer.flush()
        except KafkaError as e:
            print e


class Kafka_consumer():
    '''
    消费模块: 通过不同groupid消费topic里面的消息
    '''

    def __init__(self, kafkahost, kafkaport, kafkatopic, groupid):
        self.kafkaHost = kafkahost
        self.kafkaPort = kafkaport
        self.kafkatopic = kafkatopic
        self.groupid = groupid
        self.key = key
        self.consumer = KafkaConsumer(self.kafkatopic, group_id=self.groupid,
                                      bootstrap_servers='{kafka_host}:{kafka_port}'.format(
                                          kafka_host=self.kafkaHost,
                                          kafka_port=self.kafkaPort)
                                      )

    def consume_data(self):
        try:
            for message in self.consumer:
                yield message
        except KeyboardInterrupt, e:
            print e


def main(xtype, group, key):
    '''
    测试consumer和producer
    '''
    if xtype == "p":
        # 生产模块
        producer = Kafka_producer(KAFAKA_HOST, KAFAKA_PORT, KAFAKA_TOPIC, key)
        print "===========> producer:", producer
        for _id in range(100):
            params = '{"msg" : "%s"}' % str(_id)
            producer.sendjsondata(params)
            time.sleep(1)

    if xtype == 'c':
        # 消费模块
        consumer = Kafka_consumer(KAFAKA_HOST, KAFAKA_PORT, KAFAKA_TOPIC, group)
        print "===========> consumer:", consumer
        message = consumer.consume_data()
        for msg in message:
            print 'msg---------------->', msg
            print 'key---------------->', msg.key
            print 'offset---------------->', msg.offset


if __name__ == '__main__':
    xtype = sys.argv[1]
    group = sys.argv[2]
    key = sys.argv[3]
    main(xtype, group, key)
