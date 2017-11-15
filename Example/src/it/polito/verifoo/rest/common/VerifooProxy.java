package it.polito.verifoo.rest.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import com.microsoft.z3.Context;
import com.microsoft.z3.DatatypeExpr;

import it.polito.verifoo.rest.jaxb.Connections;
import it.polito.verifoo.rest.jaxb.FName;
import it.polito.verifoo.rest.jaxb.Hosts;
import it.polito.verifoo.rest.jaxb.NFFG;
import it.polito.verifoo.rest.jaxb.Node;
import it.polito.verifoo.rest.jaxb.VNFCatalog;
import it.polito.verigraph.mcnet.components.Checker;
import it.polito.verigraph.mcnet.components.NetContext;
import it.polito.verigraph.mcnet.components.Network;
import it.polito.verigraph.mcnet.components.NetworkObject;
import it.polito.verigraph.mcnet.components.Tuple;
import it.polito.verigraph.mcnet.netobjs.*;

public class VerifooProxy {
	    private Context ctx;
	    private NetContext nctx;
	    private Network net;
	    private HashMap<Node,NetworkObject> netobjs;
	    private ArrayList<Tuple<NetworkObject,ArrayList<DatatypeExpr>>> adm;
		public Checker check;
	    public VerifooProxy(Context ctx,NFFG nffg,Hosts hosts,Connections conns,VNFCatalog vnf){
			this.ctx=ctx;
			ArrayList<String> nodesname=new ArrayList<String>();
			ArrayList<String> nodesip=new ArrayList<String>();
		    List<Node> nodes=nffg.getNode();
			nodes.forEach((n)->{
				nodesname.add(n.getName());
				nodesip.add(n.getIp());
			});
			nctx = new NetContext (ctx,nodesname.toArray(),nodesip.toArray());
			net = new Network (ctx,new Object[]{nctx});
			netobjs=new HashMap<Node,NetworkObject>();
			nodes.forEach(this::generateNetworkObject);
			adm = new ArrayList<Tuple<NetworkObject,ArrayList<DatatypeExpr>>>();
			nodes.forEach(this::generateAddressMapping);
		    net.setAddressMappings(adm);
		    //TODO routing table and acl
		    net.attach((NetworkObject[]) netobjs.entrySet().stream().map((e)->e.getValue()).collect(Collectors.toList()).toArray());
		    check = new Checker(ctx,nctx,net);
	    }
	    private void generateAddressMapping(Node n){
			ArrayList<DatatypeExpr> al = new ArrayList<DatatypeExpr>();
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
					netobjs.put(n,new PolitoMailClient(ctx,new Object[]{nctx.nm.get(n.getName()),net,nctx}));
					break;
				}
				case MAIL_SERVER:{
					netobjs.put(n,new PolitoMailServer(ctx,new Object[]{nctx.nm.get(n.getName()),net,nctx}));
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
					netobjs.put(n,new PolitoWebClient(ctx,new Object[]{nctx.nm.get(n.getName()),net,nctx}));
					break;
				}
				case WEB_SERVER:{
					netobjs.put(n,new PolitoWebServer(ctx,new Object[]{nctx.nm.get(n.getName()),net,nctx}));
					break;
				}
				default:{
					System.err.println("Braiiinssssssssssss!");
					break;
				}
			}
		}
}
