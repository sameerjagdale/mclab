% =========================================================================== %
%                                                                             %
% Copyright 2011 Anton Dubrau and McGill University.                          %
%                                                                             %
%   Licensed under the Apache License, Version 2.0 (the "License");           %
%   you may not use this file except in compliance with the License.          %
%   You may obtain a copy of the License at                                   %
%                                                                             %
%       http://www.apache.org/licenses/LICENSE-2.0                            %
%                                                                             %
%   Unless required by applicable law or agreed to in writing, software       %
%   distributed under the License is distributed on an "AS IS" BASIS,         %
%   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  %
%   See the License for the specific language governing permissions and       %
%  limitations under the License.                                             %
%                                                                             %
% =========================================================================== %

function [time, output, valid] = drv_mbrt(scale)

% computes mandelbrot set

N = round(6000*sqrt(scale)); % set N
Nmax = round(10^3*sqrt(scale)); % set Nmax

t1 = clock;

set=mandelbrot(N, Nmax);

t2 = clock;

% Compute the running time in seconds
time = (t2-t1)*[0, 0, 86400, 3600, 60, 1]';

% Store the benchmark output
output = {mean(mean(set(:)))};

% Validate the result
t = Nmax*N;
if abs(sum(sum(set))/(t)-0.37429481997515) < 0.01;
	valid = 'PASS';
else
	valid = 'FAIL';
end

end



