import scala.io.Source
object Binary {
	
	def  binarySearch(arr:Array[Int],el: Int, first:Int, last:Int):Int={
		if(first<=last)
		{
			var mid: Int =(first+last)/2
			if(arr(mid)==el){
				return mid;
			}
			else if(arr(mid)<el){
				binarySearch(arr, el, mid+1, last)
			}
			else {
				binarySearch(arr, el, first, mid-1)
			}

		}
		else{
		return -1
		}
	}

	def main(args:Array[String]){
		println("Enter number of elements in array")
		var n =Console.readInt
		val t1=System.currentTimeMillis()		
		/*var k:Int=0;
		for(line <- Source.fromFile("demo.txt").getLines){
			k=k+1			
		}*/
		var arr=new Array[Int](n)
		for( i <- 0 to n-1){
			arr(i)=Console.readInt
		}
		//var j:Int=0
	/*	for(line <- Source.fromFile("demo.txt").getLines){
			arr(j)=line.toInt
			j=j+1			
		}*/
		arr=arr.sorted
		//var n:Int=arr.length
		for( i <- 0 to n-1){
			print(arr(i)+" ")
		}
		println("enter element to be searched")
		var el=Console.readInt
		var index:Int = binarySearch(arr, el, 0, n-1)
		if(index==(-1)){
			println("Number not found")
		}
		else{
			println("Found at" + index)
		}
		println((System.currentTimeMillis()-t1))
	}
}
