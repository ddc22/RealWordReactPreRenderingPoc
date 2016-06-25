
var Record = React.createClass({
    render: function(){
        console.log(this.props.attributes);
        return(
            <div className="records-container">
            <span>Hospital Name: </span><span>{ this.props.attributes.hospitalName } |</span>
        <span> Rating: </span><span>{ this.props.attributes.score }</span>
        </div>
        )
    }
});



var HospitalPerformance = React.createClass({
    getInitialState: function() {
        return {
            records:[]
        };
    },
    componentDidMount: function() {
        this.serverRequest = $.get("http://localhost:8081/perfjson.json", function (result) {
            var srv_records = result;
            this.setState({
                records: srv_records
            });
        }.bind(this));
    },

    componentWillUnmount: function() {
        this.serverRequest.abort();
    },
    render: function(){
        console.log(this.state.records);

        var recordNodes = this.state.records.map(function (record, index) {
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
    ReactDOMServer.renderToString(<HospitalPerformance data={commentsData}/>);
}


