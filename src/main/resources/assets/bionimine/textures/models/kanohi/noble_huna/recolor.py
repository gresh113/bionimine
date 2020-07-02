from PIL import Image,ImageColor,ImageDraw
nameList = ["gray","red","green","black","white","blue","brown",
            "gold","orange","yellow","purple","teal","lime_green","sand_blue","light_blue",
            "burnt_orange","tan",]
ogName = nameList[0]
paletteImage = Image.open('palettes.png', mode='r')
image = Image.open(ogName+'.png', mode='r')


def getPallette(x):
    iteratedPalette = []
    for i in range(5):
        #print(paletteImage.getpixel((x,i)))
        iteratedPalette.append(paletteImage.getpixel((x,i)))
   # print("-palette end-")
    return iteratedPalette
paletteOriginal = getPallette(0)
   
width = 20
height = 20
paletteWidth = paletteImage.size[0] - 1

for palette in range(1,paletteWidth):
    imageNew = image.copy()
    currentPalette = getPallette(palette)
    currentName = nameList[palette]
    pixelMap = imageNew.load()
    for x in range(width):
        for y in range(height):
            currentPixel = pixelMap[x,y]
            if (currentPixel != (255,255,255,0)):
                for j in range(5):
                    #print ("owch!")
                    #print (currentPixel)
                    jPalette = paletteOriginal[j]
                    if (currentPixel == jPalette):
                        #print('hello from the other side')
                        imageNew.putpixel((x,y),currentPalette[j])
            
    imageNew.save(currentName + '.png')


    

        

