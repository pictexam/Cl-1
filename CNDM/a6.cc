#include "ns3/netanim-module.h"
#include "ns3/core-module.h"
#include "ns3/csma-module.h"
#include "ns3/network-module.h"
#include "ns3/internet-module.h"
#include "ns3/point-to-point-module.h"
#include "ns3/applications-module.h"

using namespace ns3;

NS_LOG_COMPONENT_DEFINE ("FirstScriptExample");

int
main (int argc, char *argv[])
{
	Time::SetResolution (Time::NS);
	LogComponentEnable ("UdpEchoClientApplication", LOG_LEVEL_INFO);
	LogComponentEnable ("UdpEchoServerApplication", LOG_LEVEL_INFO);

	NodeContainer n1;
	n1.Create (4);
	NodeContainer n2;
	n2.Create (4);
	NodeContainer n3;
	n3.Create (4);
	
	InternetStackHelper stack;
	stack.Install (n1);
	stack.Install (n2);
	stack.Install (n3);
//==========================================================================	
  	CsmaHelper csma;
  	csma.SetChannelAttribute ("DataRate", StringValue ("100Mbps"));
  	csma.SetChannelAttribute ("Delay", TimeValue (NanoSeconds (6560)));

	PointToPointHelper serverServerLink;
	serverServerLink.SetDeviceAttribute ("DataRate", StringValue ("2Mbps"));
	serverServerLink.SetChannelAttribute ("Delay", StringValue ("5ms"));

//==========================================================================
	Ipv4AddressHelper address; 
	NetDeviceContainer csmaDevices,p2pDevices;
	Ipv4InterfaceContainer csmaInterfaces,p2pInterfaces;

	csmaDevices=csma.Install(n1);
  	address.SetBase ("192.168.1.0", "255.255.255.0");
  	csmaInterfaces = address.Assign (csmaDevices);

	p2pDevices=serverServerLink.Install(n1.Get (0), n2.Get (0));
	address.SetBase ("192.168.2.0", "255.255.255.0");
	p2pInterfaces=address.Assign (p2pDevices);

	csmaDevices=csma.Install(n2);
  	address.SetBase ("192.168.3.0", "255.255.255.0");
  	csmaInterfaces = address.Assign (csmaDevices);

	p2pDevices=serverServerLink.Install(n2.Get (0), n3.Get (0));
	address.SetBase ("192.168.4.0", "255.255.255.0");
	p2pInterfaces=address.Assign (p2pDevices);

	csmaDevices=csma.Install(n3);
  	address.SetBase ("192.168.5.0", "255.255.255.0");
  	csmaInterfaces = address.Assign (csmaDevices);

//==========================================================================	 

	UdpEchoServerHelper echoServer (9);

	ApplicationContainer serverApps = echoServer.Install (n3.Get (2));
	serverApps.Start (Seconds (1.0));
	serverApps.Stop (Seconds (10.0));
  	UdpEchoClientHelper echoClient (csmaInterfaces.GetAddress (2), 9);
  	echoClient.SetAttribute ("MaxPackets", UintegerValue (1));
  	echoClient.SetAttribute ("Interval", TimeValue (Seconds (1.0)));
  	echoClient.SetAttribute ("PacketSize", UintegerValue (1024));
  	ApplicationContainer clientApps = echoClient.Install (n1.Get (1));
  	clientApps.Start (Seconds (2.0));
  	clientApps.Stop (Seconds (10.0));

//==========================================================================
	Ipv4GlobalRoutingHelper::PopulateRoutingTables ();
	AnimationInterface anim ("animation.xml");

  	AnimationInterface::SetConstantPosition(n1.Get(0),7.5,7.5);
  	AnimationInterface::SetConstantPosition(n2.Get(0),22.5,15);
  	AnimationInterface::SetConstantPosition(n2.Get(1),22.5,22.5);
  	AnimationInterface::SetConstantPosition(n1.Get(1),0,0);
  	AnimationInterface::SetConstantPosition(n1.Get(2),7.5,0);
  	AnimationInterface::SetConstantPosition(n1.Get(3),0,7.5);
  	AnimationInterface::SetConstantPosition(n2.Get(2),22.5,7.5);
  	AnimationInterface::SetConstantPosition(n2.Get(3),30,15);
  	AnimationInterface::SetConstantPosition(n3.Get(0),7.5,22.5);
  	AnimationInterface::SetConstantPosition(n3.Get(1),0,22.5);
  	AnimationInterface::SetConstantPosition(n3.Get(2),0,30);
  	AnimationInterface::SetConstantPosition(n3.Get(3),7.5,30);

	Simulator::Run ();
	Simulator::Destroy ();
	return 0;
}
