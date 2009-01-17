/*
    Copyright 2008 Wolfgang Ginolas

    This file is part of P2PVPN.

    P2PVPN is free software: you can redistribute it and/or modify
    it under the terms of the GNU Lesser General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    Foobar is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public License
    along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
*/

/*
 * Main.java
 *
 * Created on 29. Oktober 2008, 11:43
 */

package org.p2pvpn.gui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.Writer;
import java.net.URL;
import java.util.Map;

import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.StreamHandler;
import javax.swing.ImageIcon;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import net.sbbi.upnp.impls.InternetGatewayDevice;
import org.p2pvpn.network.PeerID;
import org.p2pvpn.network.ConnectionManager;
import org.p2pvpn.network.Router;
import org.p2pvpn.network.RoutungTableListener;
import org.p2pvpn.network.UPnPPortForward;
import org.p2pvpn.network.UPnPPortForwardListener;

/**
 *
 * @author  wolfgang
 */
public class InfoWindow extends javax.swing.JFrame implements RoutungTableListener, UPnPPortForwardListener {
	private static final long serialVersionUID = -7583281386025886297L;
	
	private ConnectionManager connectionManager;
	private PeerID addrShown = null;
	
	/** Creates new form Main */
    public InfoWindow(ConnectionManager cm) {
        this.connectionManager = cm;
    	setLocationByPlatform(true);
    	
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                initComponents();
				startLogging();
				try {
					URL url = InfoWindow.class.getClassLoader().getResource("resources/images/P2PVPN-32.png");
					setIconImage(new ImageIcon(url).getImage());
				} catch(NullPointerException e) {}
				peerTable1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent e) {
						for(int i=e.getFirstIndex(); i<=e.getLastIndex(); i++) {
							if (peerTable1.getSelectionModel().isSelectedIndex(i)) {
								peerSelected(i);
								break;
							}
						}
					}
				});
            	setLocalInfo(
            			"ID: "+connectionManager.getLocalAddr()+
            			"  Port: "+connectionManager.getServerPort());
				connectionManager.getRouter().addTableListener(InfoWindow.this);
				//connectionManager.getUPnPPortForward().addListener(Main.this);
				upnpText.setText("disabled");
            	setVisible(true);
            }
        });
        
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        peerInfo = new javax.swing.JTextArea();
        aPanel1 = new javax.swing.JPanel();
        connectBtn1 = new javax.swing.JButton();
        hostConnectText1 = new javax.swing.JTextField();
        localInfo1 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        peerTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ipTable = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        upnpText = new javax.swing.JTextArea();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        logText = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("P2PVPN");

        jSplitPane1.setDividerLocation(250);
        jSplitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jSplitPane1.setContinuousLayout(true);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Info"));

        peerInfo.setColumns(20);
        peerInfo.setEditable(false);
        peerInfo.setRows(5);
        peerInfo.setText(" ");
        jScrollPane3.setViewportView(peerInfo);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 524, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                .addContainerGap())
        );

        jSplitPane1.setRightComponent(jPanel2);

        aPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Connections"));

        connectBtn1.setText("Connect To");
        connectBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eventConnectTo(evt);
            }
        });

        hostConnectText1.setToolTipText("host:port");

        localInfo1.setText(" ");

        peerTable1.setModel(new PeerTableModel(connectionManager));
        peerTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane4.setViewportView(peerTable1);

        javax.swing.GroupLayout aPanel1Layout = new javax.swing.GroupLayout(aPanel1);
        aPanel1.setLayout(aPanel1Layout);
        aPanel1Layout.setHorizontalGroup(
            aPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(aPanel1Layout.createSequentialGroup()
                .addGroup(aPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(aPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(localInfo1, javax.swing.GroupLayout.DEFAULT_SIZE, 524, Short.MAX_VALUE))
                    .addGroup(aPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(connectBtn1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(hostConnectText1, javax.swing.GroupLayout.DEFAULT_SIZE, 434, Short.MAX_VALUE))
                    .addGroup(aPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 524, Short.MAX_VALUE)))
                .addContainerGap())
        );
        aPanel1Layout.setVerticalGroup(
            aPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, aPanel1Layout.createSequentialGroup()
                .addComponent(localInfo1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(aPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(hostConnectText1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(connectBtn1))
                .addContainerGap())
        );

        jSplitPane1.setLeftComponent(aPanel1);

        jTabbedPane1.addTab("Connections", jSplitPane1);

        ipTable.setModel(new IPTableModel(connectionManager));
        jScrollPane1.setViewportView(ipTable);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 536, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Known IPs", jPanel1);

        upnpText.setColumns(20);
        upnpText.setRows(5);
        jScrollPane2.setViewportView(upnpText);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 536, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("UPnP", jPanel3);

        logText.setColumns(20);
        logText.setRows(5);
        jScrollPane5.setViewportView(logText);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 536, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Log", jPanel4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 568, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 473, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void eventConnectTo(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eventConnectTo
	connectionManager.connectTo(hostConnectText1.getText());
}//GEN-LAST:event_eventConnectTo

	private void peerSelected(int i) {
		if (i<0) {
			return;
		}
		
		addrShown = ((PeerTableModel)peerTable1.getModel()).getPeerID(i);
		tableChanged(null);
	}	

	public void setLocalInfo(String s) {
		localInfo1.setText(s);
	}
	
    public static void open(ConnectionManager cm) {
    	new InfoWindow(cm);
    }

	public void tableChanged(Router router) {
		StringBuffer info = new StringBuffer();
		info.append("Info for "+addrShown+"\n\n");
		Map<String, String> map = connectionManager.getRouter().getPeerInfo(addrShown);
		if (map==null) {
			peerInfo.setText("");
			return;
		}
		
		for(Map.Entry<String, String> e : map.entrySet()) {
			info.append(e.getKey()+"="+e.getValue()+"\n");
		}
		
		peerInfo.setText(info.toString());
	}

	public void upnpChanged(UPnPPortForward upnp) {
		InternetGatewayDevice igd = upnp.getIgd();
		if (igd!=null) {
			upnpText.setText("Internet Gateway Device: "+igd.getIGDRootDevice().getModelName()+"\n"+
					"External IP: "+upnp.getExternalIP()+"\n" +
					"Port mapped: "+upnp.isMapped()+"\n" +
					"Error: "+upnp.getError());
		} else {
			upnpText.setText("Internet Gateway Device: not found");
		}
	}
	
	public void startLogging() {
		LoggingWriter lt = new LoggingWriter();
		lt.setFormatter(new SimpleFormatter());
		
		Logger.getLogger("").addHandler(lt);
	}
	
	class LoggingWriter extends Handler {

		public LoggingWriter() {
			super();
		}
		
		@Override
		public void publish(LogRecord r) {
			String s = getFormatter().format(r);
			Document d = logText.getDocument();
			try {
				d.insertString(d.getLength(), s, null);
			} catch (BadLocationException ex) {
				ex.printStackTrace();
				assert false;
			}
		}

		@Override
		public void flush() {
		}

		@Override
		public void close() throws SecurityException {
		}
		

	}
	
	// TODO remove & rename variables
	
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel aPanel1;
    private javax.swing.JButton connectBtn1;
    private javax.swing.JTextField hostConnectText1;
    private javax.swing.JTable ipTable;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel localInfo1;
    private javax.swing.JTextArea logText;
    private javax.swing.JTextArea peerInfo;
    private javax.swing.JTable peerTable1;
    private javax.swing.JTextArea upnpText;
    // End of variables declaration//GEN-END:variables


    
}
