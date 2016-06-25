var React = require('react')

var Main = React.createClass({
  render: function(){
    return(
      <div>
        Hello From Main
        {this.props.children}

      </div>
    )
  }
})

module.exports = Main;
