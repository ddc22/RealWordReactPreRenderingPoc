var USER_DATA={
	name:"Dehan de Croos",
	username:"jdc91",
	image:"https://avatars3.githubusercontent.com/u/3422709?v=3&s=460"
}

var React = require('react');
var ReactDOM = require('react-dom');
var $ = require('jquery');

var HelloWorld = React.createClass({
	render: function(){
		console.log(this.props);
		return (
			<div>Hello {this.props.name}</div>
		)
	}
});

var ProfilePic = React.createClass({
	render: function(){
		return <img src={this.props.imageUrl} style={{height:100, width:100}}/>
	}
});

var ProfileLink = React.createClass({
	render: function(){
		return(
			<div>
				<a href={'https://www.github.com/'+ this.props.username}>
					{this.props.username}
				</a>
			</div>
		)
	}
});

var ProfileName = React.createClass({
	render: function(){
		return(
			<div>{this.props.name}</div>
		)
	}
});

var Avatar = React.createClass({
	render: function(){
		//Should wrap everything inside a div, span container component!
		return(
			<div>
			<ProfilePic imageUrl={this.props.user.image}/>
			<ProfileName name={this.props.user.name}/>
			<ProfileLink username={this.props.user.username}/>
			</div>
		)
	}
});


var HospitalName = React.createClass({
	render: function(){
		// console.log(this.props.data);
		return(
			<span>Hospital Name: { this.props.data.hospitalName } </span>
		)
	}
});

var Rating = React.createClass({
	render: function(){
		// console.log(this.props.data);
		return(
			<span>Hospital Name: { this.props.data.score } </span>
		)

	}
});

var Record = React.createClass({
	render: function(){
		return(
			<div className="record">
				<HospitalName data={this.props.attributes} />
				<Rating data={this.props.attributes} />
			</div>
		)
	}
});



var HospitalPerformance = React.createClass({
	render: function(){
		var recordNodes = this.props.data.map(function (record, index) {
				return (
						<Record attributes={record} key={index}/>
				);
		});
		return(
			<div className="records-container">
				{recordNodes}
			</div>
		)
	}
});




var renderInClient = function(){
	console.log("NORMAL RENDERING PROCESS STARTED");
	var nr_start = Date.now();
	$.get("http://localhost:8081/perfjson.json", function (result) {

		var records = result;
    ReactDOM.render(
        <HospitalPerformance data={records}/>
        ,document.getElementById('app')
    );
		var nr_end = Date.now();
		var nr_diff = nr_end-nr_start;
		console.log("NORMAL RENDERING DONE");
		console.log("NORMAL RENDERING ::: RENDER TOOK :" +nr_diff);
	});
};
renderInClient();

//Server side code
var renderInServer = function(comments){
    var commentsData = Java.from(comments);
    ReactDOMServer.renderToString(<HospitalPerformance data={commentsData}/>);
}

var preRenderInClient = function(){
	console.log("PRE RENDERING PROCESS STARTED");
	var pr_start = Date.now();
	$.get("http://localhost:8081/perfjson/preRender.json", function (result) {
			var htmlString = result;
			console.log(htmlString);
			var pre_render = document.getElementById('pre-rendered-app');
			pre_render.insertAdjacentHTML('beforeend', result.htmlString);
			var pr_end = Date.now();
			var pr_diff = pr_end-pr_start;
			console.log("PRE RENDERING DONE");
			console.log("PRE RENDERING ::: RENDER TOOK :" +pr_diff);
	});
};
preRenderInClient();
