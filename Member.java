public class Member {
	private final int ID;
	private String name;
	private String address;
	private int age;
	private int totalMile;

	private State currentStatus;
	
	public Member(int id, String name, String address, int age){
		this.ID      = id;
		this.name    = name;
		this.address = address;
		this.age     = age;
		this.totalMile = 0;
		this.currentStatus = Normal.getInstance();
	}

	private void setStatus(){
		State prevStatus = currentStatus;
		if(totalMile < 25000){
			currentStatus = Normal.getInstance();
		}
		else if(totalMile < 50000){
			currentStatus = Silver.getInstance();
		}
		else{
			currentStatus = Gold.getInstance();
		}
		if (prevStatus != currentStatus){
			System.out.println(name + " became " + currentStatus + " member");
		}
	}
	
	private int getPrice(int p){
		return currentStatus.getPrice(p);
	}
	
	private int calculateMile(int m){
		return currentStatus.calculateMile(m);
	}

	public void flight(int distance){
		if(distance <= 0){
			throw new IllegalArgumentException("argument must be positive val.");
		}
		System.out.println(name + " flighted " + distance + "km");
		int m = calculateMile((int)(distance * 0.6));
		totalMile += m;
		System.out.println(name + " earned " + m + " mile");
		System.out.println(name + " has " + totalMile + " mile in total");
		setStatus();
		System.out.println("-----");
	}

	public void shopping(int price){
		if(price <= 0){
			throw new IllegalArgumentException("argument must be positive val.");
		}
		System.out.println(name + " bought something for " + price + "yen");
		System.out.println("Discount price in " + currentStatus + " member: " + getPrice(price) + " yen");
		int m = calculateMile((int)(price * 0.01));
		totalMile += m;
		System.out.println(name + " earned " + m + " mile");
		System.out.println(name + " has " + totalMile + " mile in total");
		setStatus();
		System.out.println("-----");
	}

	public void useMile(int mile){
		if(mile <= 0){
			throw new IllegalArgumentException("argument must be positive val.");
		}
		if(totalMile >= mile) {
			totalMile -= mile;
			System.out.println(name + " used " + mile + " mile");
			System.out.println(name + " has " + totalMile + " mile in total");
			setStatus();
		}
		else{
			System.err.println("lack of mile!");
		}
		System.out.println("-----");
	}

	public String infoDetail(){
		StringBuilder sb = new StringBuilder();
		sb.append("Member " + ID + ":\n");
		sb.append("Name : " + name + "\n");
		sb.append("Address : " + address + "\n");
		sb.append("age : " + age + "\n");
		sb.append("<" + name + "> has " + totalMile + " mile in total\n");
		sb.append("<" + name + "> is " + currentStatus + " member\n");
		sb.append("-----\n");
		return sb.toString();
	}
	public String infoLine(){
		return ID + ", " + name + ", " + address + ", " + age + ", " + totalMile + ", " + currentStatus;
	}
	public String nameAndID(){
		return name + " (ID: " + ID + ")";
	}
	public int getID(){
		return ID;
	}

	public int getTotalMile(){
		return totalMile;
	}
}
