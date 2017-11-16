package it.polito.verifoo.rest.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import com.microsoft.z3.Context;
import com.microsoft.z3.DatatypeExpr;
import com.microsoft.z3.Status;

import it.polito.verifoo.components.RoutingTable;
import it.polito.verifoo.rest.jaxb.*;
import it.polito.verigraph.mcnet.components.*;
import it.polito.verigraph.mcnet.netobjs.*;

public class VerifooProxy {
	    private Context ctx;
	    private NetContext nctx;
	    private Network net;
	    private HashMap<Node,NetworkObject> netobjs;
	    private ArrayList<Tuple<NetworkObject,ArrayList<DatatypeExpr>>> adm;
		public Checker check;
	    public VerifooProxy(NFFG nffg,Hosts hosts,Connections conns,VNFCatalog vnf) throws BadNffgException{
			HashMap<String, String> cfg = new HashMap<String, String>();
		    cfg.put("model", "true");
		    ctx = new Context(cfg);
		    List<Node> nodes=nffg.getNode();
			String[] nodesname=new String[nodes.size()];
			String[] nodesip=new String[nodes.size()];
		    for(int i = 0; i < nodes.size(); i++){
		    	nodesname[i] = new String(nodes.get(i).getName());
				nodesip[i] = new String(nodes.get(i).getIp());
		    }
			nctx = new NetContext (ctx,nodesname,nodesip);
			//System.out.println(nctx.am);
			net = new Network (ctx,new Object[]{nctx});
			netobjs=new HashMap<Node,NetworkObject>();
			nodes.forEach(this::generateNetworkObject);
			adm = new ArrayList<Tuple<NetworkObject,ArrayList<DatatypeExpr>>>();
			nodes.forEach(this::generateAddressMapping);
		    net.setAddressMappings(adm);
		    //TODO routing table and acl
		    checkNffg(nffg);
		    netobjs.entrySet().forEach(e ->  {
							    	net.attach(e.getValue());
							    }
		    		);
		    check = new Checker(ctx,nctx,net);
	    }
	    private void generateAddressMapping(Node n){
			ArrayList<DatatypeExpr> al = new ArrayList<DatatypeExpr>();
			//System.out.println("Adding " + n.getIp() +"/"+ nctx.am.get(n.getIp()));
			al.add(nctx.am.get(n.getIp()));
			adm.add(new Tuple<>(netobjs.get(n), al));
	    }
		private void generateNetworkObject(Node n){
			FName ftype=n.getFunctionalType();
			switch (ftype) {
				case FW:{
					//TODO
					//ArrayList<Tuple<DatatypeExpr,DatatypeExpr>> acl = new ArrayList<Tuple<DatatypeExpr,DatatypeExpr>>();
					//TODO ASSAI
					netobjs.put(n,new AclFirewall(ctx,new Object[]{nctx.nm.get(n.getName()),net,nctx}));
				    
					break;
				}
				case CLASSIFIER:{					
					netobjs.put(n,new Classifier(ctx,new Object[]{nctx.nm.get(n.getName()),net,nctx}));
					break;
				}
				case DUMB:{
					netobjs.put(n,new DumbNode(ctx,new Object[]{nctx.nm.get(n.getName()),net,nctx}));
					break;
				}
				case ENDHOST:{
					netobjs.put(n,new PolitoEndHost(ctx,new Object[]{nctx.nm.get(n.getName()),net,nctx}));
					break;
				}
				case SPAM:{
					netobjs.put(n,new PolitoAntispam(ctx,new Object[]{nctx.nm.get(n.getName()),net,nctx}));
					break;
				}
				case CACHE:{
					netobjs.put(n,new PolitoCache(ctx,new Object[]{nctx.nm.get(n.getName()),net,nctx}));
					break;
				}
				case IDS:{
					netobjs.put(n,new PolitoIDS(ctx,new Object[]{nctx.nm.get(n.getName()),net,nctx}));
					break;
				}
				case MAIL_CLIENT:{
					netobjs.put(n,new PolitoEndHost(ctx,new Object[]{nctx.nm.get(n.getName()),net,nctx}));
					break;
				}
				// TODO for PolitoMailClient is needed another parameter
				case MAIL_SERVER:{
					netobjs.put(n,new PolitoEndHost(ctx,new Object[]{nctx.nm.get(n.getName()),net,nctx}));
					break;
				}
				case NAT:{					
					netobjs.put(n,new PolitoNat(ctx,new Object[]{nctx.nm.get(n.getName()),net,nctx}));
					break;
				}
				case VPN:{					
					break;
				}
				case WEB_CLIENT:{
					// TODO for PolitoWebClient is needed another parameter
					netobjs.put(n,new PolitoEndHost(ctx,new Object[]{nctx.nm.get(n.getName()),net,nctx}));
					break;
				}
				case WEB_SERVER:{
					netobjs.put(n,new PolitoEndHost(ctx,new Object[]{nctx.nm.get(n.getName()),net,nctx}));
					break;
				}
				default:{
					System.err.println("Braiiinssssssssssss!");
					break;
				}
			}
		}
		
		
		private void checkNffg(NFFG nffg) throws BadNffgException{
			List<Node> nodes = nffg.getNode();
            long nMailServer = nodes.stream()
	            	 .filter((n) -> {return n.getFunctionalType() == FName.MAIL_SERVER;})
	            	 .count();
            long nWebServer = nodes.stream()
	            	 .filter((n) -> {return n.getFunctionalType() == FName.WEB_SERVER;})
	            	 .count();
            long nMailClient = nodes.stream()
	            	 .filter((n) -> {return n.getFunctionalType() == FName.MAIL_CLIENT;})
	            	 .count();
            long nWebClient = nodes.stream()
	            	 .filter((n) -> {return n.getFunctionalType() == FName.WEB_CLIENT;})
	            	 .count();
            /*System.out.println("nMailServer: " + nMailServer +
            				   " nMailClient: " + nMailClient +
            				   " nWebServer: " + nWebServer +
            				   " nWebClient: " + nWebClient);*/
            if(nMailServer != nMailClient || nWebServer != nWebClient || nMailServer+nWebServer>1){
            	System.err.println("Only one client and one server of the same type is allowed");
            	throw new BadNffgException();
            }
            Node client = nodes.stream().filter(n -> {return n.getFunctionalType() == FName.MAIL_CLIENT || n.getFunctionalType() == FName.WEB_CLIENT;}).findFirst().get();
            Node server = nodes.stream().filter(n -> {return n.getFunctionalType() == FName.MAIL_SERVER || n.getFunctionalType() == FName.WEB_SERVER;}).findFirst().get();
            setNextHop(client, server, nffg);
			
		}
		
		private boolean setNextHop(Node source, Node server, NFFG nffg) throws BadNffgException{
			ArrayList<RoutingTable> rt = new ArrayList<RoutingTable>();
			//System.out.println("Searching next hop for " + source.getName() + " towards " + server.getName());
			if(source.getName().compareTo(server.getName()) == 0){
				return true;
			}
			List<Link> outgoingLinks = nffg.getLink().stream().filter(l -> l.getSourceNode().compareTo(source.getName()) == 0).collect(Collectors.toList());
			if(outgoingLinks.size() == 0){
				System.out.println("Route: From " + source.getName() 
									+ " to " + nctx.am.get(server.getIp()) 
									+ " -> Dead End");
				throw new BadNffgException();
			}
			for(Link link : outgoingLinks){
				Node next = nffg.getNode().stream().filter(n -> n.getName().compareTo(link.getDestNode()) == 0).findFirst().get();
				if(setNextHop(next, server, nffg)){
					System.out.println("Route: From " + source.getName() 
									+ " to " + nctx.am.get(server.getIp()) 
									+ " -> next hop: " + netobjs.get(next));
					rt.add(new RoutingTable(nctx.am.get(server.getIp()), netobjs.get(next), link.getReqLatency(), nctx.x11));
					net.routingTable2(netobjs.get(source), rt);
				}
			}
			return true;
			
		}
		
		public void checkNFFGProperty(NFFG nffg){

            Node source = nffg.getNode().stream().filter(n -> {return n.getFunctionalType() == FName.MAIL_CLIENT || n.getFunctionalType() == FName.WEB_CLIENT;}).findFirst().get();
            Node dest = nffg.getNode().stream().filter(n -> {return n.getFunctionalType() == FName.MAIL_SERVER || n.getFunctionalType() == FName.WEB_SERVER;}).findFirst().get();
            
			IsolationResult ret = this.check.checkIsolationProperty(netobjs.get(source), netobjs.get(dest));
			if (ret.result == Status.UNSATISFIABLE){
		     	   System.out.println("UNSAT"); // Nodes a and b are isolated
		    	}else{
		     		System.out.println("SAT ");
		     		System.out.print( ""+ret.model); //p.printModel(ret.model);
		     	}
		}
		
}
