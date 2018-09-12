import { ClaimOrigin } from './claimOrigin.model';
import { ClaimType } from './claimType.model';
import { ClaimStatus } from './claimStatus.mode';

export interface Claim {
  id: number;
  origin: ClaimOrigin;
  type: ClaimType;
  status: ClaimStatus;
  description: string;
  orderId: number;
  clientIdentification: string;
}
