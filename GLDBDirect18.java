//import java.util.Scanner;
import java.lang.Math;
//import java.util.Arrays;
import java.util.Random;
//import java.util.OptionalInt;
import java.io.*;

public class GLDBDirect18
{
	public static double logOfBase(int base, int num)
	{
		return Math.log(num) / Math.log(base);
	}
	
	static int noOfPeers = 25000;
	
	static int randomPeers = 15;
	static int [] peerID = new int [noOfPeers];
	static int [] randomPeersID = new int [randomPeers];
	static int [] peerIDNo = new int [noOfPeers];
	static int sourcePeerID;
	static int destPeerID;
	static int [] PeerGoodness = new int[noOfPeers];
	static int reachable=0;
	
	
	public static void main(String[] args) throws IOException
	{
		
		for (int runs=0; runs<15;runs++)
		{	
			Begin();	
			System.out.printf("Run %d of 15 completed\n", runs+1);
			System.out.println("");
		}
		//Begin().result.println("");
		//result.flush();
		//result.close();
	}
	
	public static void Begin() throws IOException
	{
		
		double totalreachable_fr = 0;
		int noOfBits = 17;
		bitNo = noOfBits-1;
		int [] ringPoints = new int [(int)Math.pow(2, noOfBits)];
		for (int index=0; index<(int)Math.pow(2, noOfBits); index++)
		{
		   ringPoints[index] = index;
		}
		
		
		//System.out.println("ID Space points: ");
				
		//System.out.println();
		//int [] PeerGoodness = new int[noOfPeers];
		
		int badProb = (int)Math.ceil(Math.pow(((500)*Math.log(noOfPeers)),4));
			for (int g=0; g<noOfPeers; g++)
			{
				Random rand = new Random();
				boolean badness = rand.nextInt(badProb)==0;
				if (badness==true)
						{
							PeerGoodness[g]=0;
						}
				else if (badness==false)
					{
					PeerGoodness[g]=1;
					}
			}
			//System.out.println("");
			
			//int [] peerID = new int [noOfPeers];
			//System.out.printf("Length of peer array:%d\n", peerID.length);
			//int [] peerIDNo = new int [noOfPeers];
			
			for (int counter=0; counter<noOfPeers; counter++)
			{
				Random generator = new Random();
				int randomIndex = generator.nextInt(ringPoints.length);
				peerID[counter] = ringPoints[randomIndex];
				peerIDNo[counter] = randomIndex;
			}
			/*String [] headers = {"PeerIDNo", "PeerID", "Peer Goodness"};
			
			System.out.println("                                                 ROUTING TABLE\n");
			
			System.out.printf("%25s%25s%25s\n", (Object[])headers );
			
			for (int row = 0; row < noOfPeers; row++)
			{
				System.out.printf("%25d", (peerIDNo[row]));
				System.out.printf("%25s", Integer.toBinaryString(peerID[row]));
				System.out.printf("%25d", PeerGoodness[row]);
				System.out.println();
			}*/
			
			int numGoodPeers = 0;
			for(int z=0; z<noOfPeers; z++)
			{
				if (PeerGoodness[z]==1)
				{
					numGoodPeers = numGoodPeers + 1;
				}
						
			}
			
			int [] goodPeers = new int [numGoodPeers];
			int ind = 0;
			
			for(int z=0; z<noOfPeers; z++)
			{
				if (PeerGoodness[z]==1)
				{
					
					goodPeers[ind]=peerID[z];
					ind = ind + 1;
				}
						
			}
			
			//System.out.printf("Number of Good Peers: %d\n", numGoodPeers);
			
			
			
			for (int counter=0; counter<randomPeers; counter++)
			{
				//totalreachable_fr = 0;
				//System.out.println("Random peer array:");
				//for (int c=0; c<15; c++)
				//{
					Random gen = new Random();
					int randomIndex = gen.nextInt(numGoodPeers);
					randomPeersID[counter] = goodPeers[randomIndex];
					//System.out.print(randomPeersID[counter]);
					int w = GLDBDirect18.Find(randomPeersID[counter]);
					sourcePeerID= peerID[w];
					//System.out.printf("SOURCE NODE IS: %d", sourcePeerID);
					double reachable_fr = GLDBDirect18.RouteAllNodes(sourcePeerID);
					//System.out.printf("Reachable: %f", reachable_fr);
					totalreachable_fr = totalreachable_fr + reachable_fr;
					//System.out.printf("Total Reachable: %f", totalreachable_fr);
					
			}
				//System.out.println("");
			double averagereachable_fr = totalreachable_fr/randomPeers;
			System.out.printf("Average Reachable fraction of peers: %f\n", averagereachable_fr);
			PrintWriter result = new PrintWriter(new BufferedWriter(new FileWriter("DHT neww 25k peers lnN.txt", true)));
			result.println(averagereachable_fr);
			result.flush();
			result.close();
		
	}
	
	public static void BadNode()
	{
		return;
	}
		
		public static double RouteAllNodes(int sourcePeerID)
		{
			int source = sourcePeerID;
			for (int c= 0; c<noOfPeers; c++)
			{
				if (source == peerID[c])//Do not route to yourself, if you are the source peer
				{
				
				}
				else//Route to every other peer in the network, if you are the source peer
				{
				hop = 0;
				destPeerID = peerID[c];
				//System.out.printf("DEST NODE IS: %d", destPeerID);
				GLDBDirect18.StartRoute(source, 1);
				}
			}
			double reachable_fract= (double)reachable/(double)(noOfPeers-1);
			reachable =0;
			return reachable_fract;	
		}
		
		static int bitNo;		
		static String next = "0";
		static int nextint = 0;
		static int hop = 0;
		static int res = 0;
		
		public static int getBit (int dest, int pos) //Gets the value of the bit at position pos (from the right) in the destination node ID dest
		{
			int bit;
			String x = "";
			while (dest > 0)
	//PROVIDE AN EXCEPTION FOR THE CASE WHERE DESTINATION NODE IS 000
			{
				int a = dest%2;
				x = a + x;
				dest = dest/2;
			}
			int l = x.length();
			if ((l-pos) >= 0 && (x.charAt(l-pos)=='1'))
			{
				bit = 1;
			}
			else
			{
				bit = 0;
			}
			return bit;
		}
		
		public static void StartRoute(int a, int b) //a is source node, b is position of next bit of the destination node ID to be pushed in
		{
			int pred, succ;
			pred = a - 1;
			succ = a + 1;
			//outerLoop:
			/*for (int k=0; k<noOfPeers; k++)
				{
					for (int j=0; j<noOfPeers; j++)
					{
						if ((int)peerID[k]==(int)a & (int)peerID[j]==(int)destPeerID)
						{*/
							int indexOfDest = GLDBDirect18.Find(destPeerID);
							outerLoop:
							if (PeerGoodness[indexOfDest]==0) //if destination peer is bad, exit loop
							{
								GLDBDirect18.BadNode();
								
								break outerLoop;
							}
							else //if destination peer is good, do the following:
							{
								double log2peers = logOfBase(2, noOfPeers);
								//System.out.printf("Max no of hops before linear routing: %d", (int)Math.ceil(log2peers+8));
								//System.out.print("Linear routing starts");
								if (hop == (int)Math.ceil(log2peers+8))
								{
									if (a == (int)(Math.pow(2, bitNo + 1))-1)//if this node is located at the last ID in the ring (e.g. 111)
									{
										succ = 0;//its successor is 0 (000)
									}
									else
									{
										succ = a+1;//else its successor is the next ID 
									}
									
									if (a == 0)//if this node is located at the first ID in the ring (e.g. 000)
									{
										pred = (int)(Math.pow(2, bitNo + 1)-1);//its predecessor is the last node in the ring (e.g. 111)
									}
									else
									{
										pred = a-1;//else its predecessor is the previous ID
									}
									
									if ((int)succ==(int)destPeerID)//if the successor of this ID is the destination node
									{
										
										//hop = hop + 1;
										//System.out.printf("Successor hop ->Destination reached: %s\n", Integer.toBinaryString(succ));
										GLDBDirect18.Terminate();	
										break outerLoop;
										
									}
									
									if ((int)pred==(int)destPeerID)
									{
										
										//hop = hop + 1;
									//	System.out.printf("Predecessor hop ->Destination reached: %s\n", Integer.toBinaryString(pred));
										GLDBDirect18.Terminate();	
										break outerLoop;
										
									}
									//if neither the successor nor the predecessor of the current ring ID is the destination node, do the following: 
									int bitr = GLDBDirect18.getBit(destPeerID, b);//get the value of the bit at this new position
									if (bitr==1)
									{
										nextint = succ;//take the successor link to the next virtual node close to your destination(right link)
										//hop = hop + 1;
									}
									else 
									{
										nextint = pred;//take the predecessor link to the next virtual node close to your destination(left link)
										//hop = hop + 1;
									}
									
									while (nextint!= (int)destPeerID)
									{
									//	System.out.printf("New next(successor/predecessor edge): %s\n", Integer.toBinaryString(nextint));
										nextint = nextint + 1;
										if (nextint!= (int)destPeerID)
										{
											GLDBDirect18.Terminate();
											break outerLoop;
										}
									}
									
								}
								else
								{
									if ((pred == destPeerID) | (succ == destPeerID)) //if destination peer is the source's predecessor, take the predecessor link and stop routing
									{
										
										hop = hop + 1;
										GLDBDirect18.Terminate();	
										break outerLoop;
										
									}
									/*if (succ == destPeerID) //if destination peer is the source's successor, take the successor link and stop routing
									{
										
										hop = hop + 1;
										GLDBDirect18.Terminate();	
										break outerLoop;
										
									}*/
									else
									{
									//if destination peer is neither source's successor nor predecessor, do the following:
										while(b<bitNo+2) //while the destination rightmost bit to be pushed in is not exceeding the total number of bits in node ID
										{
											int indexOfNext = GLDBDirect18.Find(a);
											//System.out.println("indexOfNext is: ");
											//System.out.println(indexOfNext);
											if (PeerGoodness[indexOfNext]==0)
											{
												GLDBDirect18.BadNode();
												
												break outerLoop;
											}
											else
											{
												
												int bitr = GLDBDirect18.getBit(destPeerID, b);//get the value of the bit in position b of the destination node ID
												if (bitr == 1)//if the value of the bit is 1
												{
													res = a >>> 1;
													nextint = res  + (int)Math.pow(2, bitNo);//the node at the right link of source node
													next = Integer.toBinaryString(res  + (int)Math.pow(2, bitNo)); //string version of nextint
												}
												else //if the value of the bit is 0
												{
													res = a >>> 1;
													nextint = res + (int)Math.pow(0,  bitNo);//the node at the left link of source node
													next = Integer.toBinaryString(res + (int)Math.pow(0,  bitNo));//string version of nextint
												}
												
												
												hop = hop +1;
												//System.out.printf("Next hop: %s\n", next);
												Route(nextint, b+1);//Route to next hop
												break outerLoop;
												
											}
											
										}
								}

							}
							}
					}
			//	}*/
			
		
		
	public static int Find(int element)
	{
		
		int k=0;
		for (int i=0; i<noOfPeers; i++)
		{
			if (peerID[i] == element)
			{
				k=i;
				break;
			}
		}
		return k;
	}
	
	public static void Route(int a, int i)//a is new hop, i is next bit in destination to be pushed in
	{
		boolean found = false;
		Loop:
		if ((int)a==destPeerID)//if this hop is the destination node, stop routing
		{
			GLDBDirect18.Terminate();	
		}
		else //if this hop is not the destination node:
		{
			
			
		for (int k=0; k < noOfPeers; k++) //check whether the node in this new hop exists in the peer table
			{
			
				if ((int)peerID[k]==(int)a)
				{
					sourcePeerID = peerID[k];
					found = true;
					//System.out.printf("New Source: %s\n", Integer.toBinaryString(sourcePeerID));
					GLDBDirect18.StartRoute(sourcePeerID, i); //if it exists, route to it
					break;
					//}
				}
				
			}
		int succ, pred;
				if (!found)//if the node is not a real peer in the peer table
				{
					if (a == (int)(Math.pow(2, bitNo + 1))-1)//if this node is located at the last ID in the ring (e.g. 111)
					{
						succ = 0;//its successor is 0 (000)
					}
					else
					{
						succ = a+1;//else its successor is the next ID 
					}
					
					if (a == 0)//if this node is located at the first ID in the ring (e.g. 000)
					{
						pred = (int)(Math.pow(2, bitNo + 1)-1);//its predecessor is the last node in the ring (e.g. 111)
					}
					else
					{
						pred = a-1;//else its predecessor is the previous ID
					}
					
					if ((int)succ==(int)destPeerID)//if the successor of this ID is the destination node
					{
						
						hop = hop + 1;
						//System.out.printf("Successor hop ->Destination reached: %s\n", Integer.toBinaryString(succ));
						GLDBDirect18.Terminate();	
						break Loop;
						
					}
					
					if ((int)pred==(int)destPeerID)
					{
						
						hop = hop + 1;
					//	System.out.printf("Predecessor hop ->Destination reached: %s\n", Integer.toBinaryString(pred));
						GLDBDirect18.Terminate();	
						break Loop;
						
					}
					//if neither the successor nor the predecessor of the current ring ID is the destination node, do the following: 
					int bitr = GLDBDirect18.getBit(destPeerID, i);//get the value of the bit at this new position
					if (bitr==1)
					{
						nextint = succ;//take the successor link to the next virtual node close to your destination(right link)
						hop = hop + 1;
					}
					else 
					{
						nextint = pred;//take the predecessor link to the next virtual node close to your destination(left link)
						hop = hop + 1;
					}
					
					if (nextint!= (int)destPeerID)
					{
					//	System.out.printf("New next(successor/predecessor edge): %s\n", Integer.toBinaryString(nextint));
						Route(nextint, i);
					}
					else
					{
					//	System.out.printf("Next successor hop->Destination: %s\n", Integer.toBinaryString(nextint));
						GLDBDirect18.Terminate();
					}
		
				}
		}	
		
	}	
	
	public static void Terminate()
	{
		//System.out.printf("End of routing to node %d", destPeerID);
		//System.out.printf("Total number of hops taken: %d\n", hop);
	
		reachable=reachable+1;
		return;
	}
}
	
	
