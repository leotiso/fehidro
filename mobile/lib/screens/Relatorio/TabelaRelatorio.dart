import 'package:flutter/material.dart';
import 'package:intl/intl.dart';
import 'package:mobile/http/HttpService.dart';
import 'package:mobile/models/Relatorio.dart';
import 'package:mobile/screens/Avaliacao/Drawer.dart';
import 'package:mobile/screens/Relatorio/pdf/utils_pdf.dart';



class TabelaRelatorio extends StatefulWidget {
   TabelaRelatorio({Key key, this.title});

  final String title;
  @override
  _TabelaRelatorioState createState() => _TabelaRelatorioState();
}

class _TabelaRelatorioState extends State<TabelaRelatorio> {
  static String routeName = "/final";
  final HttpService httpService = HttpService();
  Relatorio relatorio;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(

        title: Text(widget.title),
      ),
      drawer: Drawers(),
      body: FutureBuilder(
        future: httpService.getFinal(),
        builder: (BuildContext context,
            AsyncSnapshot<Map<String, dynamic>> snapshot) {
          switch (snapshot.connectionState) {
            case ConnectionState.none:
              break;
            case ConnectionState.waiting:
              return Center(
                child: Column(
                  children: <Widget>[
                    CircularProgressIndicator(
                      key: Key("circular"),
                    ),
                    Text('Carregando...')
                  ],
                ),
              );
              break;
            case ConnectionState.active:
              break;
            case ConnectionState.done:
              if (snapshot.hasData) {
                Map<String, dynamic> posts = snapshot.data;
                print("snapshot" +
                    snapshot.data["itensRelatorio"][0]["soma"].toString());
                    DateTime data = DateTime.now();
                    String dataformatada = (DateFormat("'Data numérica:' dd/MM/yyyy").format(data));
                    print((DateFormat("'Data numérica:' dd/MM/yyyy").format(data)));
                    GeneratePDF generatePdf = GeneratePDF(posts,dataformatada);
                    generatePdf.generatePDFInvoice();

                 // child: Icon(Icons.print),


              }
              break;
          }
          return Center(
            child: Column(
              children: <Widget>[
                CircularProgressIndicator(
                  key: Key("circular"),
                ),
                Text('Carregando...')
              ],
            ),
          );
        },
      ),
    );
  }
}
