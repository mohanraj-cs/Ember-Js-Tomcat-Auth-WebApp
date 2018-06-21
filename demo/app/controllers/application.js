import Controller from '@ember/controller';
import $ from 'jquery';
// import $ from 'ember';
export default Controller.extend({

  isMainMenu:false,
  isLoginForm:true,
  actions:
	{
    validateUser:function()
		{
			let res=this;
			var userName= this.get('username');
			var userPassword= this.get('password');
      alert(userName+userPassword);
			$.ajax({

              type: "POST",
              url: "Hello",
              data:  {userId:userName,password:userPassword},
              success: function(data){
									if(data==1)
									{
										alert('valid user');
                    res.set('isLoginForm',false);
                    res.set('isMainMenu',true);
                    res.send('bookTicket');
                    res.send('displayRoute');
                    // res.transitionToRoute('mainmenu');
									}
									else{
										alert("Invalid User");
									}
            },
              error: function(e){
                alert('Error: ' + e);
              }
           });
		},
      bookTicket:function()
            {

              let res=this;
              // alert('Book hbs');
              res.set('displayIt',false);
              res.set('displayPassengerDetails',false);
              res.set('displayClassDetails',false);
              res.set('displaySeatDetails',false);
              res.set('displayTicketDetails',false);
              res.transitionToRoute('book-ticket');
            },
            viewDetails:function()
           {
             let res=this;
             // alert('view hbs');
             res.set('displayIt',false);
             res.set('displayPassengerDetails',false);
             res.set('displayClassDetails',false);
             res.set('displaySeatDetails',false);
             res.set('displayTicketDetails',false);
             res.transitionToRoute('view-details');
           },
             logoutUser:function()
            {
              let res=this;
              alert('Logging Out USer');
              res.set('displayIt',false);
              res.set('displayPassengerDetails',false);
              res.set('displayClassDetails',false);
              res.set('displaySeatDetails',false);
              res.set('displayTicketDetails',false);
              res.set('isMainMenu',false);
              res.set('isLoginForm',true);
              res.set('username',null);
              res.set('password',null);
              res.transitionToRoute('home');
            }
  }
});
