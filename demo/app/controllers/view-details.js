import Controller from '@ember/controller';
import $ from 'jquery';
export default Controller.extend({
  isViewbtn:true,
  PNRDetails:null,
  displayPNRDetails:false,
  ticketDetailsByPNR:null,
  displayTicketDetailsByPNR:false,
  fare:0,
  selectedPnr:null,
  selectedSource:null,
  selectedDestination:null,
  actions:
  {
              displayPNRDetails:function()
             {
               let res=this;
               // alert('in pnr js');
               $.ajax({
                         type: "POST",
                         url: "PNRDetailsServlet",
                         data:  {route:"1"},
                         success: function(data){
                    // alert(data);
                    var jsonData = JSON.parse(data);
                    res.set('displayPNRDetails',true);
                    res.set('PNRDetails',jsonData);
                    res.set('displayTicketDetailsByPNR',false);
                    res.set('isViewbtn',false);
                    // controller.set('firstName','asd');
                   // for (var i = 0; i < jsonData.length; i++) {
                   //     var counter = jsonData[i];
                   //     console.log(counter.routeId);
                   // }
                         },
                           error: function(e){
                             alert('Error: ' + e);
                           }
                        });
             },
            displayTicketByPNR:function(a,sou,des)
           {
             let res=this;
             res.set('selectedPnr',a);
             res.set('selectedSource',sou);
             res.set('selectedDestination',des);
             // alert('in ticket js');
             // alert(a);
             $.ajax({
                       type: "POST",
                       url: "TicketByPNRServlet",
                       data:  {pnrId:a},
                       success: function(data){
                  // alert(data);
                  var jsonData = JSON.parse(data);
                  res.set('displayPNRDetails',false);
                  res.set('displayTicketDetailsByPNR',true);
                  res.set('ticketDetailsByPNR',jsonData);

                       },
                         error: function(e){
                           alert('Error: ' + e);
                         }
                      });
           },
           viewDetailsReset:function()
           {
             res.set('displayPNRDetails',false);
             res.set('displayTicketDetailsByPNR',false);
             res.set('isViewbtn',true);
           }
  }
});
