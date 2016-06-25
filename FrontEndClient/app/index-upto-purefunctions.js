var USER_DATA={
	name:"Dehan de Croos",
	username:"jdc91",
	image:"https://avatars3.githubusercontent.com/u/3422709?v=3&s=460"
}

var React = require('react');
var ReactDOM = require('react-dom');

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
})


ReactDOM.render(
	<Avatar user={USER_DATA}/>
	,document.getElementById('app')
);
