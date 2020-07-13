import os
import sys
import fileinput

prefix = 'toa_crate_'
suffix = '.json'
colors = ['red','green','blue','black','brown','white']

for i in range(1,len(colors)):
    filein = prefix + colors[0] + suffix
    f1 = open(filein,'r')
    filedata = f1.read()
    f1.close()

    newdata = filedata.replace("red", colors[i])

    f2 = open('toa_crate_'+ colors[i] +'.json','w')
    f2.write(newdata)
    f2.close()
