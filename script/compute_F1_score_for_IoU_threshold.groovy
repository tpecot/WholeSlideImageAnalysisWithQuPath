import qupath.lib.gui.commands.Commands;
import qupath.lib.roi.RoiTools.CombineOp;
import org.locationtech.jts.geom.util.PolygonExtracter
import org.locationtech.jts.geom.Geometry
import org.locationtech.jts.algorithm.hull.*
import qupath.lib.roi.interfaces.ROI

// input parameters
marker1 = "estimated"
marker2 = "GroundTruth"
IoU_threshold = 0.5

// convert detected cells into annnotations
def marker1_class = getPathClass(marker1)
def detections_marker1 = getDetectionObjects()
def newAnnotations_marker1 = detections_marker1.collect {
    return PathObjects.createAnnotationObject(it.getROI(), it.getPathClass(), it.getMeasurementList())
}
newAnnotations_marker1.eachWithIndex { annotation , i ->
    annotation.setPathClass(marker1_class)
}
//removeObjects(detections_marker1, true)
addObjects(newAnnotations_marker1)

annotations_marker1 = getAnnotationObjects().findAll{p -> p.getPathClass() == getPathClass(marker1)}
annotations_marker2 = getAnnotationObjects().findAll{p -> p.getPathClass() == getPathClass(marker2)}

removeObjects(annotations_marker1, true)
//removeObjects(annotations_marker2, true)

def TruePositive = new int[annotations_marker1.size()]
def annotationsToMerge = new int[annotations_marker1.size()][annotations_marker2.size()]
def annotationsToSubtract = new int[annotations_marker1.size()][annotations_marker2.size()]

for ( i=0; i<annotations_marker1.size(); i++ ) {
    def g1 = annotations_marker1[i].getROI().getGeometry()
    for ( j=0; j<annotations_marker2.size(); j++ ) {
        def g2 = annotations_marker2[j].getROI().getGeometry()
        double distancePixels = g1.distance(g2);

        if ( distancePixels < 0.1) {
            intersection_between_ROIs = g1.intersection(g2)
            union_between_ROIs = g1.union(g2)
            intersection_over_union = intersection_between_ROIs.getArea()/union_between_ROIs.getArea()
            if ( intersection_over_union>IoU_threshold ) {
                TruePositive[i] = 1
            }
        }
        
    }
}

nb_true_positives = 0
nb_false_positives = 0
for ( i=0; i<annotations_marker1.size(); i++ ) {
    if ( TruePositive[i]==1 ) {
        nb_true_positives += 1
    }
    else {
        nb_false_positives += 1
    }
}
nb_false_negatives = annotations_marker2.size() - nb_true_positives
   
F1score = (2*nb_true_positives)/(2*nb_true_positives + nb_false_positives + nb_false_negatives)

print("F1 score: ")
print(F1score)