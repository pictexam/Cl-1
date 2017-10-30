import csv
import random
import math
import operator

def loadDataset(filename, split, trainingSet=[] , testSet=[]):
	with open(filename, 'rb') as csvfile:
		lines = csv.reader(csvfile)
		dataset = list(lines)
	for x in range(len(dataset)-1):
		for y in range(4):
			dataset[x][y] = float(dataset[x][y])
		if random.random() < split:
			trainingSet.append(dataset[x])
		else:
			testSet.append(dataset[x])
			

			

def euclideanDistance(instance1, instance2, length):
	distance = 0
	for x in range(length):
		distance += pow((instance1[x] - instance2[x]), 2)
	return math.sqrt(distance)
	
	
def getNeighbors(trainingSet, testInstance, k):
	distances = []
	length = len(testInstance)-1
	#print length
	#print len(trainingSet)
	for x in range(len(trainingSet)):
		dist = euclideanDistance(testInstance, trainingSet[x], length)
		#print dist
		distances.append((trainingSet[x], dist))
	#print distances
	distances.sort(key=operator.itemgetter(1))
	#print distances
	#print distances
	#print distances[0][0]
	#print distances[0][1]
	#print distances[1][0]
	#print distances[1][1]
	neighbors = []
	for x in range(k):
		neighbors.append(distances[x][0])
	return neighbors
	
def getResponse(neighbors):
	classVotes = {}
	for x in range(len(neighbors)):
		response = neighbors[x][-1]
		if response in classVotes:
			classVotes[response] += 1
		else:
			classVotes[response] = 1
	#print type(classVotes)
	#sortedVotes = sorted(classVotes.iteritems(), reverse=True)
	#print classVotes
	#print classVotes
	sortedVotes = sorted(classVotes.items(), key=operator.itemgetter(1), reverse=True)
	#print sortedVotes
	#print sortedVotes[0][0]
	#print sortedVotes[0][1]
	return sortedVotes[0][0]
	
def getAccuracy(testSet, predictions):
	correct = 0
	for x in range(len(testSet)):
		if testSet[x][-1] == predictions[x]:
			correct += 1
	return (correct/float(len(testSet))) * 100.0
	
# prepare data
trainingSet=[]
testSet=[]
split = 0.67
loadDataset('iris.csv', split, trainingSet, testSet)
print 'Train set: ' + str(len(trainingSet))
print 'Test set: ' + str(len(testSet))
#print (trainingSet[0])
# generate predictions
predictions=[]
k = 5

'''
trainSet = [[2, 2, 2, 2, 'a'], [4, 4, 4, 4,'b']]
testInstance = [5, 5, 5, 5]
k = 1
neighbors = getNeighbors(trainSet, testInstance, 1)
print(neighbors)

'''
'''
neighbors = [[1,1,1,'a'], [2,2,2,'b'], [3,3,3,'b']]
response = getResponse(neighbors)
print(response)

'''
for x in range(len(testSet)):
	neighbors = getNeighbors(trainingSet, testSet[x], k)
	result = getResponse(neighbors)
	predictions.append(result)
	print('> predicted=' + repr(result) + ', actual=' + repr(testSet[x][-1]))
accuracy = getAccuracy(testSet, predictions)
print('Accuracy: ' + repr(accuracy) + '%')


