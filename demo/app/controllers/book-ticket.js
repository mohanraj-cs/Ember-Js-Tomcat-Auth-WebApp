import Controller from '@ember/controller';
import $ from 'jquery';
// $(document).ready(function () {
//     let res=this;
//     // alert('injquery');
//     res.displayRoute();
//     res.send('displayRoute');
// });
export default Controller.extend({
  isBookBtn:true,
  selectedRouteId:null,
  selectedSource:null,
  selectedDestination:null,
  selectedDepartTime:null,
  selectedArrivalTime:null,
  displayIt:false,
  classDetails:null,
  displayClassDetails:false,
  seatDetails:null,
  displaySeatsDetails:false,
  passengerDetails:null,
  displayPassengerDetails:false,
  ticketDetails:null,
  displayTicketDetails:false,
  PNRDetails:null,
  displayPNRDetails:false,
  ticketDetailsByPNR:null,
  displayTicketDetailsByPNR:false,
  fare:0,
  actions:
	{
    // init: function() {
    //   let res=this;
    //   res._super();
    //   res.displayRoute();
    // },
	   displayRoute:function()
		{
      let res=this;
			$.ajax({
                  type: "POST",
                  url: "RouteServlet",
                  data:  {route:"1"},
                  success: function(data){
					  alert('success');
           var jsonData = JSON.parse(data);
           res.set('displayIt',true);
           res.set('isBookBtn',false);
           res.set('routeDetails',jsonData);
           res.set('displayPassengerDetails',false);
           res.set('displayClassDetails',false);
           res.set('displaySeatDetails',false);
           res.set('displayTicketDetails',false);
           res.set('ticketDetails',jsonData);
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

  displayClassType:function(a,sou,des,dep,arr)
     {
       let res=this;
       // alert(sou+des+dep+arr);
       res.set('selectedRouteId',a);
       res.set('selectedSource',sou);
       res.set('selectedDestination',des);
       res.set('selectedDepartTime',dep);
       res.set('selectedArrivalTime',arr);
       // alert('in Class type js');
       	var selectedRouteId= a;
        // alert(selectedRouteId);
       $.ajax({
                   type: "POST",
                   url: "ClassServlet",
                   data:  {routeId:selectedRouteId},
                   success: function(data){
            // alert(data);

            var jsonData = JSON.parse(data);
            // controller.set('firstName','asd');

           // for (var i = 0; i < jsonData.length; i++) {
           //     var counter = jsonData[i];
           //
           //     console.log(counter.routeId);
           //     // alert(counter.routeId);
           //     // alert(counter.source);
           // }

           res.set('displayIt',false);
           res.set('displayClassDetails',true);
           res.set('classDetails',jsonData);
           // var json=JSON.parse(data);
           // var json=$.parseJSON(data);
           // var obj=JSON.parse(json);
           // alert(obj);
           // alert(obj["source"]);
                 },
                   error: function(e){
                     alert('Error: ' + e);
                   }
                });
     },
     displaySeats:function(a)
        {
          let res=this;
          	var selectedClassId= a;
          $.ajax({
                      type: "POST",
                      url: "SeatsServlet",
                      data:  {classId:selectedClassId},
                      success: function(data){
               // alert(data);
               var jsonData = JSON.parse(data);
               var count=jsonData.length;
               var jsonData1=[];
               if(count==10)
               {
                 for(var i=1,k=0;i<=10 && k<10;i++,k++)
                 {
                   var obj=new Object();
                   obj.count=i;
                   obj.status=jsonData[k];
                   jsonData1.push(obj);
                 }
               }
               if(count==20)
               {
                 for(var i=11,k=0;i<=30 && k<20;i++,k++)
                 {
                     var obj=new Object();
                     obj.count=i;
                     obj.status=jsonData[k];
                     jsonData1.push(obj);
                 }
               }
              res.set('displaySeatDetails',true);
              res.set('seatDetails',jsonData1);
                    },
                      error: function(e){
                        alert('Error: ' + e);
                      }
                   });
        },
        confirmSeats:function()
           {
             let res=this;
             var seats = [];
              $.each($("input[name='selector[]']:checked"), function(){
                  seats.push($(this).val());
              });
            // alert(seats);
             $.ajax({
                         type: "POST",
                         url: "BookServlet",
                         data:  {selectedSeats:seats},
                         success: function(data){
                           // alert(data);
                        var jsonData = JSON.parse(data);

                         res.set('displayPassengerDetails',true);
                         res.set('passengerDetails',jsonData);
                       },
                         error: function(e){
                           alert('Error: ' + e);
                         }
                      });
           },
           bookSeats:function()
            {
                let res=this;
                var name=[];
                $(".passengerName").each(function() {
                    name.push($(this).val());
                });
                // alert(name);
                var age=[];
                $(".passengerAge").each(function() {
                    age.push($(this).val());
                });
                // alert(age);
                var gender=[];
                $(".passengerGender").each(function() {
                    gender.push($(this).val());
                });
                // alert(gender);
                $.ajax({
                            type: "POST",
                            url: "PassengerServlet",
                            data:  {passengerName:name,passengerAge:age,passengerGender:gender},
                            success: function(data){
                            // alert(data);
                           var jsonData = JSON.parse(data);
                            res.set('displayIt',false);
                            res.set('displayPassengerDetails',false);
                            res.set('displayClassDetails',false);
                            res.set('displaySeatDetails',false);
                            res.set('displayTicketDetails',true);
                            res.set('ticketDetails',jsonData);

                          },
                            error: function(e){
                              alert('Error: ' + e);
                            }
                         });
              },
  }

});
