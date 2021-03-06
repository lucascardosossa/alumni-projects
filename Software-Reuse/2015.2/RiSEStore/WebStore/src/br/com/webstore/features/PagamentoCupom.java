//#if ${CarrinhoCompras} == "T"
//#if ${CarrinhoFinalizarCompras} == "T"
//#if ${PgtoCupomDesconto} == "T"
package br.com.webstore.features;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import br.com.webstore.facade.GenericFacade;
//#if ${Usuario} == "T"
import br.com.webstore.model.Usuario;
//#endif
import br.com.webstore.model.Venda;

/**
 * @author webstore
 *
 */
public class PagamentoCupom extends JFrame {

	private static final long serialVersionUID = 7046684960720630980L;
	private JFormattedTextField cupomField;
	//#if ${Usuario} == "T"
	public PagamentoCupom(final GenericFacade gfacade, final Usuario usuario, String formaDePagamento, final String valorTotal) {
		final JDialog frame = new JDialog();
		this.setLayout(null);	
		this.setTitle("Cupom");
		this.setSize(400, 300);
		PagamentoCupom.this.setLocationRelativeTo(null);
		
		JLabel lbCumpom = new JLabel("Cupom: ");		
		lbCumpom.setBounds(2,10,200,15);
		this.add(lbCumpom);
		
		this.cupomField = new JFormattedTextField();
		this.cupomField.setBounds(60, 10, 219, 25);
		this.cupomField.setToolTipText("Informe o numero do cartao.");
		this.cupomField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		this.cupomField.setColumns(10);
		this.add(this.cupomField);
		
		JButton btFinalizarCompra = new JButton("Finalizar Compra");
		btFinalizarCompra.setBounds(110,210,160,23);
		this.add(btFinalizarCompra);
		btFinalizarCompra.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				//falta a valida��o Acredito que essa informa��es seriam suficiente. 
				Venda v = new Venda();
				Calendar c = Calendar.getInstance(); 					
				v.setDataVenda(c.getTime());
				v.setUsuarioVenda(usuario);
				//v.setIdUsuarioCupom(null);
				v.setValorTotal(new BigDecimal(valorTotal));
				v.setStatusVenda(gfacade.findStatusVendabyName("Finalizada"));
				v.setFormaPagamento(gfacade.findFormaPagamentoByNome("Dep�sito Banc�rio"));
				gfacade.insertVenda(v);
				JOptionPane.showMessageDialog(null, "Venda finalizada com sucesso!");
				PagamentoCupom.this.setVisible(false);
				CarrinhoCheckout.getInstance(gfacade, usuario).criarLayout(null);	
			}
		});
		
		
		this.setVisible(true);
	}
	//#endif


}
//#endif
//#endif
//#endif
