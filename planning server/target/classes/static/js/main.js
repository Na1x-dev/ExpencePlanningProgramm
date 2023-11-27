function CallPrint(strid) {
 var prtContent = document.querySelector(strid);
   var prtCSS1 = '<style>.table {width: 100%;margin-bottom: 20px;border: 1px solid #dddddd;border-collapse: collapse; }.table th {font-weight: bold;padding: 5px;background: #efefef;border: 1px solid #dddddd;}.table td {border: 1px solid #dddddd;padding: 5px;}</style>';
   var prtCSS2 = '<style>.table th{font-weight: bold;padding: 5px;background: #efefef;border: 1px solid #333333;}</style>';
   var prtCSS3 = '<style>.table td{font-weight: bold;padding: 5px;background: #efefef;border: 1px solid #333333;}</style>';
   var WinPrint = window.open('gg','wp','left=50,top=50,width=800,height=640,toolbar=0,scrollbars=1,status=0');
   WinPrint.document.write(prtCSS1);
   WinPrint.document.write('<table class="table">');
   WinPrint.document.write(prtContent.innerHTML);
   while(WinPrint.document.querySelector('.open-dialog')!=null){
        WinPrint.document.querySelector('.open-dialog').parentElement.parentElement.remove();
   }
   WinPrint.document.write('</table>');

   WinPrint.document.close();
   WinPrint.focus();
   WinPrint.print();
   WinPrint.close();
   prtContent.innerHTML=strOldOne;
}

