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
        const isLoaded = this.state.isLoaded;
        return (
            <ErrorBoundary>
                {isLoaded
                ? <PlantingList plantings={this.state.plantings} />
                : <div>Loading</div>}
            </ErrorBoundary>
        );
    }

  loadFromServer() {
        fetch("/planting")
            .then(res => res.json())
            .then(result => this.setState({plantings: result, isLoaded:true}));
    }
}

class ErrorBoundary extends React.Component {
    constructor(props) {
        super(props);
        this.state = { hasError: false };
    }

    static getDerivedStateFromError(error) {
        // Update state so the next render will show the fallback UI.
        return { hasError: true };
    }

    componentDidCatch(error, errorInfo) {
        // You can also log the error to an error reporting service
        logErrorToMyService(error, errorInfo);
    }

    render() {
        if (this.state.hasError) {
            // You can render any custom fallback UI
            return <h1>Something went wrong.</h1>;
        }

        return this.props.children;
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