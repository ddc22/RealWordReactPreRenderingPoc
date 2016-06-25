
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
    ReactDOM.render(
        <HospitalPerformance />
        ,document.getElementById('app')
    );
};

var renderInServer = function(comments){
    var commentsData = Java.from(comments);
    print("HELLO");
    return ReactDOMServer.renderToString(<HospitalPerformance data={commentsData}/>);
}


