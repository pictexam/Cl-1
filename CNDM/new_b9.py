import os
import sys
def transDelay(t,M):
	return float((M/t*1000))

def propDelay(d,S):
	return float((d/S*1000))

def main():
	d1,d2,N,M,S,p = 1,1,1,1,1,1
	print 'Enter the distance of link1'
	d1 = float(raw_input())
	print 'Enter the distance of link2'
	d2 = float(raw_input())
	print 'Enter the bandwidth of link1 in kbps'
	b1 = float(raw_input())
	print 'Enter the bandwidth of link2 in kbps'
	b2 = float(raw_input())
	print 'Enter the number of packets to be sent'
	N = float(raw_input())
	print 'Enter size of each packet in kb'
	M = float(raw_input())
	S= 3*10**8
	print 'Enter the processing delay in milliseconds'
	p = float(raw_input())

	print 'the propogation delay for link1 is '
	P1 = propDelay(d1,S)
	print P1

	print 'the propogation delay for link2 is'
	P2 = propDelay(d2,S)
	print P2

	print 'the transmission delay for link1 is'
	T1 = transDelay(M*N,b1)
	print T1

	print 'the transmission delay for link1 is'
	T2 = transDelay(M*N,b2)
	print T2

	print 'the end-to-end delay is '
	print(P1+P2+T1+T2+p)

main()