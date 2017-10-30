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
		var arr=new Array[Int](n)
		println("Enter numbers")
		for( i <- 0 to n-1){
			arr(i)=Console.readInt
		}
		arr=arr.sorted
		for( i <- 0 to n-1){
			println(arr(i)+" ")
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
	}
}