const React = require('react');
const ReactDOM = require('react-dom');

class App extends React.Component {

    constructor(props) {
        super(props);
        this.state = {plantings: [], isLoaded:false};
    }

    componentDidMount() {
       this.loadFromServer();
    }

    render() {
        let list;
        if(this.state.isLoaded){
            list =  <PlantingList plantings={this.state.plantings}/>
        }
        else {
            list = <div>Loading</div>
        }

        return (
            {list}
        )
    }

  loadFromServer() {
        fetch("/planting")
            .then(res => res.json())
            .then(result => this.setState({plantings: result, isLoaded:true}));
    }
}

class PlantingList extends React.Component{
    render() {
        const plantings = this.props.plantings.map(planting =>
            <Planting key={planting.id} planting={planting}/>
        );
        return (
            <table>
                <tbody>
                    <tr>
                        <th>Lon</th>
                        <th>Lat</th>
                        <th>Planted Time</th>
                    </tr>
                    {plantings}
                </tbody>
            </table>
        )
    }
}

class Planting extends React.Component{
    constructor(props) {
        super(props);
        this.state = {lon:null, lat:null, plantedTime:null};
    }

    render() {
        return (
            <tr>
                <td>{this.props.planting.lon}</td>
                <td>{this.props.planting.lat}</td>
                <td>{this.props.planting.plantedTime}</td>
            </tr>
        )
    }
}

ReactDOM.render(
    <App />,
    document.getElementById('react')
)