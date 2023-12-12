/*
The image name in the CSV file must match the image name in the project. If you rename one, you must rename the other. REQUIRES IMAGES TO HAVE PIXEL SIZE METADATA.
Otherwise, go through the script and change any instances of microns to px, and remove the pixelsize sections :(

Script to import a folder of exported from CytoMAP 1.4.9 or later.
Expected input:
CSV file
X,Y coordinates in first two columns
Image name in third column
All other columns are cluster or gate data
Version 3! Much faster! Improved logic to not fail when certain objects are not found (in case you delete a cell or have an empty image). Improved logic to catch cells missed by fast method.
Michael Nelson, September 2020
Updated by Thierry Pécot for QuPath 0.5.0
*/
def Aproject = getProject()

def folder = FileChoosers.promptForDirectory()
folder.listFiles().each{file->
    // Create BufferedReader
    def csvReader = new BufferedReader(new FileReader(file));
    
    
    //The rest of the script assumes the X coordinate is in column1, Y in column2, and all other columns are to be imported.
    row = csvReader.readLine() // first row (header)
    measurementNames = row.split(',')
    length = row.split(',').size()
    //measurementNames -= 'X'
    //measurementNames -= 'Y'
    print measurementNames
    print "Adding results from " + file
    print "This may take some time, please be patient"
    csv = []
    Set imageList = []
    while ((row = csvReader.readLine()) != null) {
        toAdd = row.split(',')
        imageList.add(toAdd[2])
        csv << toAdd
    }
    int t = 0
    int z = 0
    
    print imageList
    imageList.each{ image->
        entry = Aproject .getImageList().find {it.getImageName() == image}
    
        if (entry == null){print "NO ENTRIES FOR IMAGE "+ image;return;}
        imageData = entry.readImageData()
        hierarchy = imageData.getHierarchy()
        pixelSize = imageData.getServer().getPixelCalibration().getAveragedPixelSizeMicrons()
            
        csvSubset = csv.findAll{it[2] == image}
        //println("csv subset "+csvSubset)
        objects = hierarchy.getDetectionObjects()//.findAll{it.getPathClass() == getPathClass("Islet")}
        ob = new ObservableMeasurementTableData();
        ob.setImageData(imageData,  objects);
        
        csvSubset.each{line->
            x = line[0] as double
            y = line[1] as double
            object = PathObjectTools.getObjectsForLocation(hierarchy, x/pixelSize, y/pixelSize,  t, z,-1).find{it.isDetection()}
            if (object == null){print "ERROR, OBJECT NOT FOUND AT "+x+","+y;return}
/*                if (round(ob.getNumericValue(object, "Centroid X µm")) != x || round(ob.getNumericValue(object, "Centroid Y µm")) != y){
            object = objects.find{round(ob.getNumericValue(it, "Centroid X µm")) == x && round(ob.getNumericValue(it, "Centroid Y µm")) == y}
        }*/
            i=3 //skip the X Y and Image entries
            
            while (i<length){
                //toAdd = row.split(',')[i] as double
                object.getMeasurementList().putMeasurement(measurementNames[i], line[i] as double)
                i++
            }
            objects.remove(object)
        }
        entry.saveImageData(imageData)
    }
}

print "Done with all images!"

def round(double number){
    BigDecimal bd = new BigDecimal(number)
    def result
    if (number < 100){
        bd = bd.round(new MathContext(4))
        result = bd.doubleValue()
    }else if (number < 1000){
        result = number.round(2)
    }else {result = number.round(1) }

    return result
}
getCurrentViewer().getHierarchy().setHierarchy(getProjectEntry().readHierarchy())
fireHierarchyUpdate()

import qupath.lib.gui.measure.ObservableMeasurementTableData
import java.math.MathContext