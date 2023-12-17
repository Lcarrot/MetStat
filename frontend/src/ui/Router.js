import React, {BrowserRouter, Route, Routes} from "react-router-dom";
import App from "../App";
import GraphCounterTag from "../screen/GraphCounterTag";
import GraphTextCounter from "../screen/GraphTextCounter";

function PageRouter() {
    return <BrowserRouter>
        <Routes>
            <Route element={<GraphCounterTag />} path='/tag' />
            <Route element={<GraphTextCounter />} path='/text' />
            <Route element= {<div> not found </div>} path='*' />
        </Routes>
    </BrowserRouter>
}

export default PageRouter;