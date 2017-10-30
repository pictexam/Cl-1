def prune(keys,min):
	for item in keys:
		if last_table[item]<min:
			last_table.pop(item,None)
			
def get_sup_count(db,line):
    items=line.split(',')
    #print items
    count=0
    for i in db:
    	#print set(items)
    	#print set(i)
        if set(items).issubset(set(i)):
            count=count+1
    return count
			
def self_join(db,last_table,k):
	items=last_table.keys()
	last_table.clear()
	for i in range(len(items)):
		keysi=items[i].split(",")
		#print items[i]
		#print keysi
		#print "----------------"
		j=i+1
		while j<len(items):			#while and not for!! IMP!!!!!!!!!!!!!!!!
			keysj=items[j].split(",")
			#print keysj
			#print "==============="
			nset=list(set(keysi)|set(keysj))
			#print nset
			if(len(nset)==k):
				line=""
				for l in range(len(nset)-1):
					line=line+str(nset[l])+','
				line=line+str(nset[l+1])
				#print line
				last_table[line]=get_sup_count(db,line)
			j=j+1


db=[]
f=open('transactions.csv','r')

for line in f:
	line= line.replace("\n","")
	#print line
	line=line.split(",")
	#print line
	db.append(line)
print db
print "*******"

k=2
last_table={}
for transaction in db:
	for item in set(transaction):
		if (last_table.has_key(item)):
			last_table[item]+=1
		else:
			last_table[item]=1



while True:

	keys=last_table.keys()
	#print keys
	#values=last_table.values()
	#print values
	if(len(keys)<1):
		break
		
	print last_table
	print ""
	prune(keys,3)
	print last_table
	print "******"
	print ""
	self_join(db,last_table,k)
	#print last_table
	#break
	k=k+1
	
